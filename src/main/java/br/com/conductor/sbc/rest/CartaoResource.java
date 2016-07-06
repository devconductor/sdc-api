
package br.com.conductor.sbc.rest;

import static br.com.conductor.sbc.util.Constantes.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.conductor.sbc.entidades.Cartao;
import br.com.conductor.sbc.entidades.Cartao.StatusCartao;
import br.com.conductor.sbc.entidades.Conta;
import br.com.conductor.sbc.entidades.Credito;
import br.com.conductor.sbc.entidades.Transacao;
import br.com.conductor.sbc.repositorios.CartaoRepositorio;
import br.com.conductor.sbc.repositorios.ContaRepositorio;
import br.com.conductor.sbc.repositorios.CreditoRepositorio;
import br.com.conductor.sbc.repositorios.TransacaoRepositorio;
import br.com.conductor.sbc.util.Constantes;
import br.com.conductor.sbc.util.CreditCardNumberGenerator;
import br.com.conductor.sbc.util.Transacional;
import br.com.twsoftware.alfred.object.Objeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = Constantes.PATH_CARTOES, produces = MediaType.APPLICATION_JSON_VALUE, description = "Opera�oes com Cart�es", tags = { "Cartao" })
@RequestMapping(value = Constantes.API_PATH + Constantes.PATH_CARTOES, produces = MediaType.APPLICATION_JSON_VALUE)
@SuppressWarnings("rawtypes")
public class CartaoResource extends GenericResource{

     @Autowired
     private CartaoRepositorio cartaoRepositorio;

     @Autowired
     private ContaRepositorio contaRepositorio;
     
     @Autowired
     private CreditoRepositorio creditoRepositorio;
     
     @Autowired
     private TransacaoRepositorio transacaoRepositorio;
     
     @Timed
     @ResponseBody
     @ApiOperation(value = "Retona todos os cart�o de uma conta", notes = "Retona todos os cart�o de uma conta", response = Cartao.class, httpMethod = "GET", responseContainer = "List")
     @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity getAll(@ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta) {
          
          List<Cartao> findAll = cartaoRepositorio.findByConta(idConta);
          if (Objeto.notBlank(findAll)) {
               return ResponseEntity.ok(findAll);
          }

          return ResponseEntity.notFound().build();
          
     }

     @Timed
     @ResponseBody
     @ApiOperation(value = "Retorna um cart�o", notes = "Retorna um cart�o", response = Cartao.class)
     @RequestMapping(value = "/{idCartao}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity getOne(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "ID do Cart�o", required = true) @PathVariable("idCartao") Long id) {

          Cartao cartao = cartaoRepositorio.findOneByIdAndContaId(id, idConta);
          if (Objeto.notBlank(cartao)) {
               return ResponseEntity.ok(cartao);
          } else {
               return ResponseEntity.notFound().build();
          }

     }

     @Timed
     @ResponseBody
     @ApiOperation(value = "Cria um cart�o", notes = "Cria um cart�o", response = Cartao.class)
     @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity create(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "Cart�o a ser criado", required = true) @RequestBody Cartao cartao) {

          Conta conta = contaRepositorio.findOne(idConta);
          if (Objeto.isBlank(conta)) {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Conta n�o encontrada"));
          }
          
          cartao.setConta(conta);
          
          CreditCardNumberGenerator c = new CreditCardNumberGenerator();
          cartao.setNumero(c.generate("9999", 16));
          
          if (Objeto.isBlank(cartao.getId())) {
               cartao.setStatus(StatusCartao.ATIVO);
          }

          return super.create(cartao);

     }

     @Timed
     @ResponseBody
     @ApiOperation(value = "Atualiza um cart�o", notes = "Atualiza um cart�o", response = Cartao.class)
     @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity update(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "Cart�o a ser atualizado", required = true) @RequestBody Cartao cartao) {

          Conta conta = contaRepositorio.findOne(idConta);
          if (Objeto.isBlank(conta)) {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Conta n�o encontrada"));
          }
          
          Cartao cartao_ = cartaoRepositorio.findOneByIdAndContaId(cartao.getId(), idConta);
          if(Objeto.isBlank(cartao_)){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o n�o encontrado ou n�o pertence a conta informada"));
          }
          
          cartao.setConta(conta);
          cartao.setStatus(StatusCartao.ATIVO);

          return super.update(cartao);

     }

     @Timed
     @ResponseBody
     @ApiOperation(value = "Cancelar um cart�o", notes = "Cancelar um cart�o")
     @RequestMapping(value = "/{idCartao}/cancelar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity cancelar(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "Cart�o a ser cancelado", required = true) @PathVariable("idCartao") Long id) {
          
          return atualizarStatus(idConta, id, StatusCartao.CANCELADO, "Cart�o cancelado com sucesso");

     }

     @Timed
     @ResponseBody
     @ApiOperation(value = "Bloquear um cart�o.", notes = "Bloquear um cart�o.")
     @RequestMapping(value = "/{idCartao}/bloquear", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity bloquear(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "Cart�o a ser bloqueado", required = true) @PathVariable("idCartao") Long id) {
          
          
          return atualizarStatus(idConta, id, StatusCartao.BLOQUEADO, "Cart�o bloqueado com sucesso");
          
     }
     
     @Timed
     @ResponseBody
     @ApiOperation(value = "Desbloquear um cart�o.", notes = "Desbloquear um cart�o.")
     @RequestMapping(value = "/{idCartao}/desbloquear", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity desbloquear(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "Cart�o a ser desbloqueado", required = true) @PathVariable("idCartao") Long id) {
          
          return atualizarStatus(idConta, id, StatusCartao.ATIVO, "Cart�o desbloqueado com sucesso");
          
     }
     
     @Timed
     @ResponseBody
     @ApiOperation(value = "Creditar dinheiro em um cart�o", notes = "Creditar dinheiro em um cart�o")
     @RequestMapping(value = "/{idCartao}/creditar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity creditar(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "ID do Cartao a ser creditado", required = true) @PathVariable("idCartao") Long idCartao,
               @ApiParam(value = "Valor a ser creditado", required = true) @RequestBody Long valor) {
          
          ResponseEntity response = null;
          try {
               
               Cartao cartao = cartaoRepositorio.findOneByIdAndContaId(idCartao, idConta);
               if (Objeto.isBlank(cartao)) {
                    
                    response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o n�o encontrado"));
                    
               }else if(valor <= 0){
                    
                    response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json("N�o � poss�vel creditar um valor menor ou igual a zero"));
                    
               }else if(!cartao.getStatus().equals(StatusCartao.ATIVO)){
                    
                    response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json("O cart�o se encontra bloqueado"));
                    
               }else{
                    
                    Credito credito = new Credito();
                    credito.setCartao(cartao);
                    credito.setValor(valor);
                    creditoRepositorio.save(credito);
                    
                    response = ResponseEntity.ok().body(json("Credito realizado com sucesso."));
               }
               
          } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
          
          return response;
     }     
     
     @Timed
     @ResponseBody
     @ApiOperation(value = "Transacionar valores", notes = "Transacionar algum valor utilizando um determinado cart�o")
     @RequestMapping(value = "/{idCartao}/transacionar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity transacionar(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "ID do Cartao a ser creditado", required = true) @PathVariable("idCartao") Long idCartao,
               @ApiParam(value = "Valor da transa��o", required = true) @RequestBody Long valor) {
          
          ResponseEntity response = null;
          try {
               
               Cartao cartao = cartaoRepositorio.findOneByIdAndContaId(idCartao, idConta);
               if (Objeto.isBlank(cartao)) {
                    
                    response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o n�o encontrado"));
                    
               }else if(valor <= 0){
                    
                    response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json("N�o � poss�vel transacionar um valor menor ou igual a zero"));
                    
               }else if(!cartao.getStatus().equals(StatusCartao.ATIVO)){
                    
                    response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json("O cart�o se encontra bloqueado"));
                    
               }else{
                    
                    if(cartaoRepositorio.limiteDisponivel(idCartao, valor)){
                         
                         Transacao t = new Transacao();
                         t.setCartao(cartao);
                         t.setValor(valor);
                         transacaoRepositorio.save(t);
                         
                         response = ResponseEntity.ok().body(json("Transa��o realizada com sucesso."));
                         
                    }else{
                         
                         response = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(json("Limite indispon�vel."));
                         
                    }
                    
               }
               
          } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
          
          return response;
     }     
     
     @Timed
     @ResponseBody
     @ApiOperation(value = "Consultar o limite de um determinado cart�o", notes = "Consultar o limite de um determinado cart�o")
     @RequestMapping(value = "/{idCartao}/limite", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity limite(
               @ApiParam(value = "ID da Conta", required = true) @PathVariable("idConta") Long idConta,
               @ApiParam(value = "ID do Cartao a ser creditado", required = true) @PathVariable("idCartao") Long idCartao) {
          
          ResponseEntity response = null;
          try {
               
               Cartao cartao = cartaoRepositorio.findOneByIdAndContaId(idCartao, idConta);
               if (Objeto.isBlank(cartao)) {
                    
                    response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o n�o encontrado"));
                    
               }else if(!cartao.getStatus().equals(StatusCartao.ATIVO)){
                    
                    response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json("O cart�o se encontra bloqueado"));
                    
               }else{
                    
                    Long limite = cartaoRepositorio.limite(idCartao);
                    response = ResponseEntity.ok().body(limite);
                    
               }
               
          } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
          
          return response;
     }     
     
     @Timed
     @Transacional
     @ResponseBody
     @ApiOperation(value = "Transferir valores entre dois cart�es distintos", notes = "Transferir valores entre dois cart�es")
     @RequestMapping(value = "/{idCartao}transferir", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity transferir(
               @ApiParam(value = "ID do cart�o de origem", required = true) @PathVariable("idCartao") Long idCartaoOrigem,
               @ApiParam(value = "ID do cart�o de destino", required = true) @RequestParam("idCartaoDestino") Long idCartaoDestino,
               @ApiParam(value = "Valor a ser transferido", required = true) @RequestBody Long valor
               ) {
          
          ResponseEntity response = null;
          
          if(idCartaoOrigem.equals(idCartaoDestino)){
               
               response = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(json("N�o � permitido transferir valores para o mesmo cart�o."));
               
          }else{
               
               Cartao origem = cartaoRepositorio.findOne(idCartaoOrigem);
               Cartao destino = cartaoRepositorio.findOne(idCartaoDestino);
               
               if (Objeto.isBlank(origem)) {
                    
                    response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o origem n�o encontrado"));
                    
               } else if (Objeto.isBlank(destino)) {
                    
                    response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o destino n�o encontrado"));
                    
               } else {
                    
                    if (cartaoRepositorio.limiteDisponivel(idCartaoOrigem, valor)) {
                         
                         Transacao t = new Transacao();
                         t.setCartao(origem);
                         t.setValor(valor);
                         transacaoRepositorio.save(t);
                         
                         Credito c = new Credito();
                         c.setCartao(destino);
                         c.setValor(valor);
                         creditoRepositorio.save(c);
                         
                         response = ResponseEntity.ok().body(json("Transfer�ncia realizada com sucesso"));
                         
                    } else {
                         
                         response = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(json("Limite indispon�vel."));
                         
                    }
                    
               }
               
          }

          return response;
     }     

     @Override
     public CartaoRepositorio getJpaRepository() {

          return cartaoRepositorio;

     }

     private ResponseEntity atualizarStatus(Long idConta, Long id, StatusCartao status, String msg) {

          ResponseEntity response = null;
          try {
               
               if (Objeto.isBlank(idConta) || Objeto.isBlank(id)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
               }

               Cartao cartao = cartaoRepositorio.findOneByIdAndContaId(id, idConta);
               if (Objeto.notBlank(cartao)) {

                    cartao.setStatus(status);
                    cartaoRepositorio.save(cartao);
                    response = ResponseEntity.ok().body(json(msg));

               } else {

                    response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(json("Cart�o n�o encontrado"));

               }

          } catch (Exception e) {
               response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }

          return response;
     }     
}
