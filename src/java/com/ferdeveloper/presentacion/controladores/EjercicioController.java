/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferdeveloper.presentacion.controladores;

import com.ferdeveloper.presentacion.json.JsonTransformer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ferdeveloper.negocio.dominio.Ejercicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ferdeveloper.persistencia.dao.EjercicioDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EjercicioController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    EjercicioDAO ejercicioDAO;

    // GET
    @RequestMapping(value = "/ejercicio/{idEjercicio}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idEjercicio") int idEjercicio) {
        try {
            Ejercicio ejercicio = ejercicioDAO.get(idEjercicio);
            String jsonSalida = jsonTransformer.toJSON(ejercicio);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } //       catch (BussinessException ex) {
        //            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //             
        //        } 
        catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }

    // INSERT
    @RequestMapping(value = "/ejercicio", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            Ejercicio ejercicio = (Ejercicio) jsonTransformer.fromJSON(jsonEntrada, Ejercicio.class);
            ejercicioDAO.insert(ejercicio);
            String jsonSalida = jsonTransformer.toJSON(ejercicio);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } //        catch (BussinessException ex) {
        //            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //             
        //        } 
        catch (Exception ex) {
            //httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(ex);

        }
    }

    // UPDATE
    @RequestMapping(value = "/ejercicio/{idEjercicio}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idEjercicio") int idEjercicio) {
        try {
            Ejercicio ejercicio = (Ejercicio) jsonTransformer.fromJSON(jsonEntrada, Ejercicio.class);
            ejercicioDAO.update(ejercicio);
            String jsonSalida = jsonTransformer.toJSON(ejercicio);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } //        catch (BussinessException ex) {
        //            List<BussinessMessage> bussinessMessage = ex.getBussinessMessages();
        //            String jsonSalida = jsonTransformer.toJson(bussinessMessage);
        //
        //            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //            httpServletResponse.setContentType("application/json; charset=UTF-8");
        //            try {
        //                httpServletResponse.getWriter().println(jsonSalida);
        //            } catch (IOException ex1) {
        //                Logger.getLogger(SeguroMedicoController.class.getName()).log(Level.SEVERE, null, ex1);
        //            }
        //
        //        } 
        catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(EjercicioController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    // DELETE
    @RequestMapping(value = "/ejercicio/{idEjercicio}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idEjercicio") int idEjercicio) {
        try {
            ejercicioDAO.delete(idEjercicio);

            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } 
//        catch (BussinessException ex) {
//            List<BussinessMessage> bussinessMessage = ex.getBussinessMessages();
//            String jsonSalida = jsonTransformer.toJson(bussinessMessage);
//
//            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            httpServletResponse.setContentType("application/json; charset=UTF-8");
//            try {
//                httpServletResponse.getWriter().println(jsonSalida);
//            } catch (IOException ex1) {
//                Logger.getLogger(SeguroMedicoController.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//
//        } 
        catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

    }

    // LIST
    @RequestMapping(value = "/ejercicios", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            List<Ejercicio> ejercicios = ejercicioDAO.findAll();
            String jsonSalida = jsonTransformer.toJSON(ejercicios);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } //        catch (BussinessException ex) {
        //            List<BussinessMessage> bussinessMessage = ex.getBussinessMessages();
        //            String jsonSalida = jsonTransformer.toJson(bussinessMessage);
        //
        //            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //            httpServletResponse.setContentType("application/json; charset=UTF-8");
        //            try {
        //                httpServletResponse.getWriter().println(jsonSalida);
        //            } catch (IOException ex1) {
        //                Logger.getLogger(SeguroMedicoController.class.getName()).log(Level.SEVERE, null, ex1);
        //            }
        //
        //        } 
        catch (Exception ex) {
            throw new RuntimeException(ex);

        }
    }
    
    // FIND BY CATEGORIA
    @RequestMapping(value = "/categoria-ejercicio/{categoriaEjercicio}", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("categoriaEjercicio") String categoriaEjercicio) {
        try {
            List<Ejercicio> ejercicios = ejercicioDAO.findByCategoria(categoriaEjercicio);
            String jsonSalida = jsonTransformer.toJSON(ejercicios);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } //        catch (BussinessException ex) {
        //            List<BussinessMessage> bussinessMessage = ex.getBussinessMessages();
        //            String jsonSalida = jsonTransformer.toJson(bussinessMessage);
        //
        //            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //            httpServletResponse.setContentType("application/json; charset=UTF-8");
        //            try {
        //                httpServletResponse.getWriter().println(jsonSalida);
        //            } catch (IOException ex1) {
        //                Logger.getLogger(SeguroMedicoController.class.getName()).log(Level.SEVERE, null, ex1);
        //            }
        //
        //        } 
        catch (Exception ex) {
            throw new RuntimeException(ex);

        }
    }
}
