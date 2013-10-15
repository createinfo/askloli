package io.loli.askloli.controller.jsp;

import io.loli.askloli.entity.Survey;
import io.loli.askloli.entity.User;
import io.loli.askloli.service.impl.SurveyServiceImpl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.view.Viewable;

@Named
@Path("/user/manage/survey")
public class SurveyManageController {
    @Context
    UriInfo uriInfo;
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;

    @Inject
    @Named("surveyService")
    private SurveyServiceImpl ss;

    @Path("/new")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response newSurveySetup() {
        return Response.ok(new Viewable("/user/manage/survey/newSurvey", null))
                .build();
    }

    @Path("/new")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response newSurveySubmit(@FormParam(value = "title") String title,
            @FormParam(value = "description") String description) {
        System.out.println(description);
        try {
            description = new String(description.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Survey survey = new Survey();
        survey.setCreator((User) request.getSession().getAttribute("user"));
        survey.setTitle(title);
        survey.setDescription(description);
        ss.save(survey);
        return Response.seeOther(
                uriInfo.getBaseUriBuilder()
                        .path("/user/manage/survey/edit/" + survey.getId())
                        .build()).build();
    }

    @Path("/{id}/edit")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response editSurveySetup(@PathParam("id") int id) {
        Survey survey = ss.findById(id);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("survey", survey);
        return Response.ok(new Viewable("/user/manage/survey/edit", map))
                .build();
    }

    @Path("/{id}/edit")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response editSurveySubmit(@PathParam("id") int id) {
        // TODO 验证id和参数中的id是否一致，权限验证
        
        return Response.seeOther(
                uriInfo.getBaseUriBuilder().path("/user/manage/survey/" + id)
                        .build()).build();
    }

}
