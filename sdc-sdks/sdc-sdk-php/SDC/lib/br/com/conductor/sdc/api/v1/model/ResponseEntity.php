<?php
/**
 * ResponseEntity
 *
 * PHP version 5
 *
 * @category Class
 * @package  br.com.conductor.sdc.api.v1.invoker
 * @author   http://github.com/swagger-api/swagger-codegen
 * @license  http://www.apache.org/licenses/LICENSE-2.0 Apache Licene v2
 * @link     https://github.com/swagger-api/swagger-codegen
 */
/**
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */

namespace br.com.conductor.sdc.api.v1.model;

use \ArrayAccess;
/**
 * ResponseEntity Class Doc Comment
 *
 * @category    Class
 * @description 
 * @package     br.com.conductor.sdc.api.v1.invoker
 * @author      http://github.com/swagger-api/swagger-codegen
 * @license     http://www.apache.org/licenses/LICENSE-2.0 Apache Licene v2
 * @link        https://github.com/swagger-api/swagger-codegen
 */
class ResponseEntity implements ArrayAccess
{
    /**
      * Array of property to type mappings. Used for (de)serialization 
      * @var string[]
      */
    static $swaggerTypes = array(
        'body' => 'object',
        'status_code' => 'string'
    );
  
    static function swaggerTypes() {
        return self::$swaggerTypes;
    }

    /** 
      * Array of attributes where the key is the local name, and the value is the original name
      * @var string[] 
      */
    static $attributeMap = array(
        'body' => 'body',
        'status_code' => 'statusCode'
    );
  
    static function attributeMap() {
        return self::$attributeMap;
    }

    /**
      * Array of attributes to setter functions (for deserialization of responses)
      * @var string[]
      */
    static $setters = array(
        'body' => 'setBody',
        'status_code' => 'setStatusCode'
    );
  
    static function setters() {
        return self::$setters;
    }

    /**
      * Array of attributes to getter functions (for serialization of requests)
      * @var string[]
      */
    static $getters = array(
        'body' => 'getBody',
        'status_code' => 'getStatusCode'
    );
  
    static function getters() {
        return self::$getters;
    }

    
    /**
      * $body 
      * @var object
      */
    protected $body;
    
    /**
      * $status_code 
      * @var string
      */
    protected $status_code;
    

    /**
     * Constructor
     * @param mixed[] $data Associated array of property value initalizing the model
     */
    public function __construct(array $data = null)
    {
        
        if ($data != null) {
            $this->body = $data["body"];
            $this->status_code = $data["status_code"];
        }
    }
    
    /**
     * Gets body
     * @return object
     */
    public function getBody()
    {
        return $this->body;
    }
  
    /**
     * Sets body
     * @param object $body 
     * @return $this
     */
    public function setBody($body)
    {
        
        $this->body = $body;
        return $this;
    }
    
    /**
     * Gets status_code
     * @return string
     */
    public function getStatusCode()
    {
        return $this->status_code;
    }
  
    /**
     * Sets status_code
     * @param string $status_code 
     * @return $this
     */
    public function setStatusCode($status_code)
    {
        $allowed_values = array("100", "101", "102", "103", "200", "201", "202", "203", "204", "205", "206", "207", "208", "226", "300", "301", "302", "302", "303", "304", "305", "307", "308", "400", "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "413", "414", "414", "415", "416", "417", "418", "419", "420", "421", "422", "423", "424", "426", "428", "429", "431", "500", "501", "502", "503", "504", "505", "506", "507", "508", "509", "510", "511");
        if (!in_array($status_code, $allowed_values)) {
            throw new \InvalidArgumentException("Invalid value for 'status_code', must be one of '100', '101', '102', '103', '200', '201', '202', '203', '204', '205', '206', '207', '208', '226', '300', '301', '302', '302', '303', '304', '305', '307', '308', '400', '401', '402', '403', '404', '405', '406', '407', '408', '409', '410', '411', '412', '413', '413', '414', '414', '415', '416', '417', '418', '419', '420', '421', '422', '423', '424', '426', '428', '429', '431', '500', '501', '502', '503', '504', '505', '506', '507', '508', '509', '510', '511'");
        }
        $this->status_code = $status_code;
        return $this;
    }
    
    /**
     * Returns true if offset exists. False otherwise.
     * @param  integer $offset Offset 
     * @return boolean
     */
    public function offsetExists($offset)
    {
        return isset($this->$offset);
    }
  
    /**
     * Gets offset.
     * @param  integer $offset Offset 
     * @return mixed 
     */
    public function offsetGet($offset)
    {
        return $this->$offset;
    }
  
    /**
     * Sets value based on offset.
     * @param  integer $offset Offset 
     * @param  mixed   $value  Value to be set
     * @return void
     */
    public function offsetSet($offset, $value)
    {
        $this->$offset = $value;
    }
  
    /**
     * Unsets offset.
     * @param  integer $offset Offset 
     * @return void
     */
    public function offsetUnset($offset)
    {
        unset($this->$offset);
    }
  
    /**
     * Gets the string presentation of the object
     * @return string
     */
    public function __toString()
    {
        if (defined('JSON_PRETTY_PRINT')) {
            return json_encode(\br.com.conductor.sdc.api.v1.invoker\ObjectSerializer::sanitizeForSerialization($this), JSON_PRETTY_PRINT);
        } else {
            return json_encode(\br.com.conductor.sdc.api.v1.invoker\ObjectSerializer::sanitizeForSerialization($this));
        }
    }
}
