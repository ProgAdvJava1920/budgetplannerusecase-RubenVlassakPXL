package be.pxl.student.rest;

import be.pxl.student.entity.Payment;
import be.pxl.student.service.AccountService;
import be.pxl.student.util.exception.AccountNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/accounts")
public class AccountsRest {
    @Inject
    private AccountService accountService;

    @GET
    @Path("{accountName}")
    @Produces("application/json")
    public Response getAccounts(@PathParam("accountName") String name) {
        List<Payment> result;
        try {
            result = accountService.findPaymentsByAccountName(name);
        } catch (AccountNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(result).build();
    }
}
