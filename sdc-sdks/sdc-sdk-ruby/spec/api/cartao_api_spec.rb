=begin
PIER Labs

PIER Labs - Primeiro Hach Day SBC UFPB

OpenAPI spec version: 0.0.1
Contact: pierlabs@conductor.com.br
Generated by: https://github.com/swagger-api/swagger-codegen.git

License: Apache 2.0
http://www.apache.org/licenses/LICENSE-2.0.html

Terms of Service: http://pierlabs.io/terms/

=end

require 'spec_helper'
require 'json'

# Unit tests for SDC::CartaoApi
# Automatically generated by swagger-codegen (github.com/swagger-api/swagger-codegen)
# Please update as you see appropriate
describe 'CartaoApi' do
  before do
    # run before each test
    @instance = SDC::CartaoApi.new
  end

  after do
    # run after each test
  end

  describe 'test an instance of CartaoApi' do
    it 'should create an instact of CartaoApi' do
      @instance.should be_a(SDC::CartaoApi)
    end
  end

  # unit tests for bloquear_using_put
  # Bloquear um cart\u00EF\u00BF\u00BDo.
  # Bloquear um cart\u00EF\u00BF\u00BDo.
  # @param id_conta ID da Conta
  # @param id_cartao Cart\u00EF\u00BF\u00BDo a ser bloqueado
  # @param [Hash] opts the optional parameters
  # @return [ResponseEntity]
  describe 'bloquear_using_put test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for cancelar_using_delete
  # Cancelar um cart\u00EF\u00BF\u00BDo
  # Cancelar um cart\u00EF\u00BF\u00BDo
  # @param id_conta ID da Conta
  # @param id_cartao Cart\u00EF\u00BF\u00BDo a ser cancelado
  # @param [Hash] opts the optional parameters
  # @return [ResponseEntity]
  describe 'cancelar_using_delete test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for create_using_post
  # Cria um cart\u00EF\u00BF\u00BDo
  # Cria um cart\u00EF\u00BF\u00BDo
  # @param id_conta ID da Conta
  # @param cartao Cart\u00EF\u00BF\u00BDo a ser criado
  # @param [Hash] opts the optional parameters
  # @return [Cartao]
  describe 'create_using_post test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for creditar_using_put
  # Creditar dinheiro em um cart\u00EF\u00BF\u00BDo
  # Creditar dinheiro em um cart\u00EF\u00BF\u00BDo
  # @param id_conta ID da Conta
  # @param id_cartao ID do Cartao a ser creditado
  # @param valor Valor a ser creditado
  # @param [Hash] opts the optional parameters
  # @return [ResponseEntity]
  describe 'creditar_using_put test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for desbloquear_using_put
  # Desbloquear um cart\u00EF\u00BF\u00BDo.
  # Desbloquear um cart\u00EF\u00BF\u00BDo.
  # @param id_conta ID da Conta
  # @param id_cartao Cart\u00EF\u00BF\u00BDo a ser desbloqueado
  # @param [Hash] opts the optional parameters
  # @return [ResponseEntity]
  describe 'desbloquear_using_put test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for extratos_using_post
  # Retonar os extratos de transa\u00EF\u00BF\u00BD\u00EF\u00BF\u00BDes do cart\u00EF\u00BF\u00BDo
  # Retorna os extratos de todas as transa\u00EF\u00BF\u00BD\u00EF\u00BF\u00BDes de um determinado cart\u00EF\u00BF\u00BDo
  # @param id_conta ID da Conta
  # @param id_cartao ID do cart\u00EF\u00BF\u00BDo
  # @param [Hash] opts the optional parameters
  # @return [ResponseEntity]
  describe 'extratos_using_post test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for get_all_using_get
  # Retona todos os cart\u00EF\u00BF\u00BDo de uma conta
  # Retona todos os cart\u00EF\u00BF\u00BDo de uma conta
  # @param id_conta ID da Conta
  # @param [Hash] opts the optional parameters
  # @return [Array<Cartao>]
  describe 'get_all_using_get test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for get_one_using_get
  # Retorna um cart\u00EF\u00BF\u00BDo
  # Retorna um cart\u00EF\u00BF\u00BDo
  # @param id_conta ID da Conta
  # @param id_cartao ID do Cart\u00EF\u00BF\u00BDo
  # @param [Hash] opts the optional parameters
  # @return [Cartao]
  describe 'get_one_using_get test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for limite_using_get
  # Consultar o limite de um determinado cart\u00EF\u00BF\u00BDo
  # Consultar o limite de um determinado cart\u00EF\u00BF\u00BDo
  # @param id_conta ID da Conta
  # @param id_cartao ID do Cartao a ser creditado
  # @param [Hash] opts the optional parameters
  # @return [ResponseEntity]
  describe 'limite_using_get test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for transacionar_using_put
  # Transacionar valores
  # Transacionar algum valor utilizando um determinado cart\u00EF\u00BF\u00BDo
  # @param id_conta ID da Conta
  # @param id_cartao ID do Cartao a ser creditado
  # @param valor Valor da transa\u00EF\u00BF\u00BD\u00EF\u00BF\u00BDo
  # @param [Hash] opts the optional parameters
  # @return [ResponseEntity]
  describe 'transacionar_using_put test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for transferir_using_post
  # Transferir valores entre dois cart\u00EF\u00BF\u00BDes distintos
  # Transferir valores entre dois cart\u00EF\u00BF\u00BDes
  # @param id_conta ID da Conta do cart\u00EF\u00BF\u00BDo de origem
  # @param id_cartao ID do cart\u00EF\u00BF\u00BDo de origem
  # @param id_cartao_destino ID do cart\u00EF\u00BF\u00BDo de destino
  # @param valor Valor a ser transferido
  # @param [Hash] opts the optional parameters
  # @return [ResponseEntity]
  describe 'transferir_using_post test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

  # unit tests for update_using_put
  # Atualiza um cart\u00EF\u00BF\u00BDo
  # Atualiza um cart\u00EF\u00BF\u00BDo
  # @param id_conta ID da Conta
  # @param cartao Cart\u00EF\u00BF\u00BDo a ser atualizado
  # @param [Hash] opts the optional parameters
  # @return [Cartao]
  describe 'update_using_put test' do
    it "should work" do
      # assertion here
      # should be_a()
      # should be_nil
      # should ==
      # should_not ==
    end
  end

end