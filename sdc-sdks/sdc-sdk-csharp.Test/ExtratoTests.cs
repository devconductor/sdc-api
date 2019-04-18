using NUnit.Framework;

using System;
using System.Linq;
using System.IO;
using System.Collections.Generic;
using Conductor.SDC.Api;
using Conductor.SDC.Model;
using Conductor.SDC.Client;
using System.Reflection;

namespace Conductor.SDC.Test
{
    /// <summary>
    ///  Class for testing Extrato
    /// </summary>
    /// <remarks>
    /// This file is automatically generated by Swagger Codegen.
    /// Please update the test case below to test the model.
    /// </remarks>
    [TestFixture]
    public class ExtratoTests
    {
        private Extrato instance;

        /// <summary>
        /// Setup before each test
        /// </summary>
        [SetUp]
        public void Init()
        {
            instance = new Extrato();
        }
    
        /// <summary>
        /// Clean up after each test
        /// </summary>
        [TearDown]
        public void Cleanup()
        {

        }   

        /// <summary>
        /// Test an instance of Extrato
        /// </summary>
        [Test]
        public void ExtratoInstanceTest()
        {
            Assert.IsInstanceOf<Extrato> (instance, "instance is a Extrato");
        }

        
        /// <summary>
        /// Test the property 'Data' 
        /// </summary>
        [Test]
        public void DataTest()
        {
            // TODO: unit test for the property 'Data' 
        }
        
        /// <summary>
        /// Test the property 'Msg' 
        /// </summary>
        [Test]
        public void MsgTest()
        {
            // TODO: unit test for the property 'Msg' 
        }
        
        /// <summary>
        /// Test the property 'Tipo' 
        /// </summary>
        [Test]
        public void TipoTest()
        {
            // TODO: unit test for the property 'Tipo' 
        }
        
        /// <summary>
        /// Test the property 'Valor' 
        /// </summary>
        [Test]
        public void ValorTest()
        {
            // TODO: unit test for the property 'Valor' 
        }
        

    }

}
