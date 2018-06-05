package erteltest.controllers;

import erteltest.exceptions.InternalException;
import erteltest.exceptions.NotFoundException;
import erteltest.helpers.CakeConverter;
import erteltest.models.CakeDto;
import erteltest.models.CakeFilter;
import erteltest.models.CakeView;
import erteltest.models.StatusType;
import erteltest.services.DefaultCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/cakes")
public class CakeController {

    private final DefaultCakeService cakeService;

    @Autowired
    public CakeController(DefaultCakeService cakeService) {
        this.cakeService = cakeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params = {"page", "limit", "text", "statuses[]"})
    @ResponseBody
    public CakeView getView(@RequestParam(value = "page") Integer page,
                            @RequestParam(value = "limit") Integer limit,
                            @RequestParam(value = "text") String text,
                            @RequestParam(value = "statuses[]") String[] statuses) {
        CakeFilter cakeFilter = new CakeFilter();
        cakeFilter.setPage(page);
        cakeFilter.setLimit(limit);
        cakeFilter.setText(text);
        if (statuses != null) {
            StatusType[] statusTypes = new StatusType[statuses.length];
            for (int i = 0; i < statuses.length; i++) {
                statusTypes[i] = StatusType.valueOf(statuses[i]);
            }
            cakeFilter.setStatuses(statusTypes);
        }
        try {
            CakeView result = cakeService.getView(cakeFilter).get();
            if (result != null) {
                return result;
            } else {
                throw new NotFoundException();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CakeDto getItem(@PathVariable Long id) {
        try {
            CakeDto result = cakeService.getItem(id).get();
            if (result != null) {
                return result;
            } else {
                throw new NotFoundException();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void saveItem(@RequestBody CakeDto cakeDto) {
        cakeService.saveItem(CakeConverter.cakeDTOToCake(cakeDto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeItem(@PathVariable(value = "id") Long id) {
        try {
            if (cakeService.ifExists(id).get()) {
                cakeService.removeItem(id);
            } else {
                throw new NotFoundException();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new InternalException();
        }
    }
}
