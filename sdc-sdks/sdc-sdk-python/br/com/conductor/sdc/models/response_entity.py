# coding: utf-8

"""
Copyright 2016 SmartBear Software

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

    Ref: https://github.com/swagger-api/swagger-codegen
"""

from pprint import pformat
from six import iteritems


class ResponseEntity(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self):
        """
        ResponseEntity - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'body': 'object',
            'status_code': 'str'
        }

        self.attribute_map = {
            'body': 'body',
            'status_code': 'statusCode'
        }

        self._body = None
        self._status_code = None

    @property
    def body(self):
        """
        Gets the body of this ResponseEntity.


        :return: The body of this ResponseEntity.
        :rtype: object
        """
        return self._body

    @body.setter
    def body(self, body):
        """
        Sets the body of this ResponseEntity.


        :param body: The body of this ResponseEntity.
        :type: object
        """
        self._body = body

    @property
    def status_code(self):
        """
        Gets the status_code of this ResponseEntity.


        :return: The status_code of this ResponseEntity.
        :rtype: str
        """
        return self._status_code

    @status_code.setter
    def status_code(self, status_code):
        """
        Sets the status_code of this ResponseEntity.


        :param status_code: The status_code of this ResponseEntity.
        :type: str
        """
        allowed_values = ["100", "101", "102", "103", "200", "201", "202", "203", "204", "205", "206", "207", "208", "226", "300", "301", "302", "302", "303", "304", "305", "307", "308", "400", "401", "402", "403", "404", "405", "406", "407", "408", "409", "410", "411", "412", "413", "413", "414", "414", "415", "416", "417", "418", "419", "420", "421", "422", "423", "424", "426", "428", "429", "431", "500", "501", "502", "503", "504", "505", "506", "507", "508", "509", "510", "511"]
        if status_code not in allowed_values:
            raise ValueError(
                "Invalid value for `status_code`, must be one of {0}"
                .format(allowed_values)
            )
        self._status_code = status_code

    def to_dict(self):
        """
        Returns the model properties as a dict
        """
        result = {}

        for attr, _ in iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value

        return result

    def to_str(self):
        """
        Returns the string representation of the model
        """
        return pformat(self.to_dict())

    def __repr__(self):
        """
        For `print` and `pprint`
        """
        return self.to_str()

    def __eq__(self, other):
        """
        Returns true if both objects are equal
        """
        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """
        Returns true if both objects are not equal
        """
        return not self == other

