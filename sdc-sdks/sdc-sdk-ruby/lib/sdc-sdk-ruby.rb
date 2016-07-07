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

# Common files
require 'sdc-sdk-ruby/api_client'
require 'sdc-sdk-ruby/api_error'
require 'sdc-sdk-ruby/version'
require 'sdc-sdk-ruby/configuration'

# Models
require 'sdc-sdk-ruby/models/cartao'
require 'sdc-sdk-ruby/models/conta'
require 'sdc-sdk-ruby/models/response_entity'

# APIs
require 'sdc-sdk-ruby/api/cartao_api'
require 'sdc-sdk-ruby/api/conta_api'

module SDC
  class << self
    # Customize default settings for the SDK using block.
    #   SDC.configure do |config|
    #     config.username = "xxx"
    #     config.password = "xxx"
    #   end
    # If no block given, return the default Configuration object.
    def configure
      if block_given?
        yield(Configuration.default)
      else
        Configuration.default
      end
    end
  end
end