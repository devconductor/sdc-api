
package br.com.conductor.sbc.rest;

import static br.com.conductor.sbc.util.Constantes.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.conductor.sbc.entidades.Cartao;
import br.com.conductor.sbc.entidades.Cartao.StatusCartao;
import br.com.conductor.sbc.repositorios.CartaoRepositorio;
import br.com.conductor.sbc.util.Constantes;
import br.com.conductor.sbc.util.CreditCardNumberGenerator;
import br.com.twsoftware.alfred.object.Objeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = Constantes.PATH_CARTAO, produces = MediaType.APPLICATION_JSON_VALUE, description = "Opera�oes com Cart�es", tags = { "Cartao" })
@RequestMapping(value = Constantes.API_PATH + Constantes.PATH_CARTAO, produces = MediaType.APPLICATION_JSON_VALUE)
@SuppressWarnings("rawtypes")
public class CartaoResource extends GenericResource{

     @Autowired
     private CartaoRepositorio cartaoRepositorio;

     @Timed
     @ResponseBody
     @ApiOperation(value = Constantes.PATH_CARTAO + "", notes = "Retorna todos os cart�es", response = Cartao.class, httpMethod = "GET", responseContainer = "List")
     @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity getAll() {

          return super.getAll();
     }

     @Timed
     @ResponseBody
     @ApiOperation(value = Constantes.PATH_CARTAO + "/{id}", notes = "Retorna um cart�o", response = Cartao.class)
     @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity getOne(@PathVariable("id") Long id) {

          return super.getOne(id);

     }

     @Timed
     @ResponseBody
     @ApiOperation(value = Constantes.PATH_CARTAO, notes = "Cria um cart�o", response = Cartao.class)
     @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity create(@RequestBody Cartao cartao) {

          if (Objeto.notBlank(cartao)) {
               
               CreditCardNumberGenerator c = new CreditCardNumberGenerator();
               cartao.setNumero(c.generate("9999", 16));
               
               if(Objeto.isBlank(cartao.getId())){
                    cartao.setStatus(StatusCartao.ATIVO);
               }
               
          }

          return super.createOrUpdate(cartao);

     }

     @Timed
     @ResponseBody
     @ApiOperation(value = Constantes.PATH_CARTAO, notes = "Atualiza um cart�o", response = Cartao.class)
     @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity update(@RequestBody Cartao cartao) {

          return super.createOrUpdate(cartao);

     }

     @Timed
     @ResponseBody
     @ApiOperation(value = Constantes.PATH_CARTAO + "/{id}/cancelar", notes = "Cancelar um cart�o")
     @RequestMapping(value = "/{id}/cancelar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity cancelar(@PathVariable("id") Long id) {
          
          ResponseEntity response = null;
          try {

               if (Objeto.isBlank(id)) {
                    
                    Cartao cartao = cartaoRepositorio.findOne(id);
                    if (Objeto.notBlank(cartao)) {
                         
                         cartao.setStatus(StatusCartao.CANCELADO);
                         cartaoRepositorio.save(cartao);
                         response = ResponseEntity.ok().body(json("Cart�o cancelado com sucesso"));
                         
                    } else {
                         
                         response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o n�o encontrado"));
                         
                    }                    

               } else {
                    
                    response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    
               }

          } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }

          return response;

     }
     
     @Timed
     @ResponseBody
     @ApiOperation(value = Constantes.PATH_CARTAO + "/{id}/bloquear", notes = "Bloquear um cart�o.")
     @RequestMapping(value = "/{id}/bloquear", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity bloquear(@PathVariable("id") Long id) {
          
          ResponseEntity response = null;
          try {
               
               if (Objeto.isBlank(id)) {
                    
                    Cartao cartao = cartaoRepositorio.findOne(id);
                    if (Objeto.notBlank(cartao)) {
                         
                         cartao.setStatus(StatusCartao.BLOQUEADO);
                         cartaoRepositorio.save(cartao);
                         response = ResponseEntity.ok().body(json("Cart�o bloqueado com sucesso"));
                         
                    } else {
                         
                         response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o n�o encontrado"));
                         
                    }                    
                    
               } else {
                    
                    response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    
               }
               
          } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
          
          return response;
          
     }
     
     @Timed
     @ResponseBody
     @ApiOperation(value = Constantes.PATH_CARTAO + "/{id}/desbloquear", notes = "Desbloquear um cart�o.")
     @RequestMapping(value = "/{id}/desbloquear", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity desbloquear(@PathVariable("id") Long id) {
          
          ResponseEntity response = null;
          try {
               
               if (Objeto.isBlank(id)) {
                    
                    Cartao cartao = cartaoRepositorio.findOne(id);
                    if (Objeto.notBlank(cartao)) {
                         
                         cartao.setStatus(StatusCartao.ATIVO);
                         cartaoRepositorio.save(cartao);
                         response = ResponseEntity.ok().body(json("Cart�o desbloqueado com sucesso"));
                         
                    } else {
                         
                         response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o n�o encontrado"));
                         
                    }                    
                    
               } else {
                    
                    response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    
               }
               
          } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
          
          return response;
          
     }

     @Override
     public CartaoRepositorio getJpaRepository() {

          return cartaoRepositorio;

     }

}
