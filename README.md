

 Vulnerability :Dynamic Code Evaluation: Unsafe Deserialization
 Vulnerability Description in Detail : Deserializing user-controlled object streams at runtime can allow attackers to execute arbitrary code on the server, abuse application logic, and/or lead to denial of service.
 Likely Impact : Deserializing user-controlled object streams at runtime can allow attackers to execute arbitrary code on the server, abuse application logic, and/or lead to denial of service.
 Recommendation : If possible, do not deserialize untrusted data without validating the contents of the object stream. In order to validate classes being deserialized, the look-ahead deserialization pattern should be used. The object stream will first contain the class description metadata and then the serialized bytes of their member fields. The Java serialization process enables developers to read the class description and decide whether to proceed with the deserialization of the object or abort it. In order to do so, it is necessary to subclass java.io.ObjectInputStream and provide a custom implementation of the resolveClass(ObjectStreamClass desc) method where class validation and verification should take place. There are existing implementations of the look-ahead pattern that can be easily used, such as the Apache Commons IO (org.apache.commons.io.serialization.ValidatingObjectInputStream). Always use a strict allow list approach to only deserialize expected types. A deny list approach is not recommended since attackers may use many available gadgets to bypass the deny list. Also, keep in mind that although some classes to achieve code execution are publicly known, there may be others that are unknown or undisclosed, so an allow list approach will always be preferred. Any class allowed in the allow list should be audited to make sure it is safe to deserialize. When deserialization takes place in a library or framework (for example, when using JMX, RMI, JMS, HTTP Invokers), the preceding recommendation is not useful since it is beyond the developer's control. In those cases, you may want to make sure that these protocols meet the following requirements: - Not exposed publicly. - Use authentication. - Use integrity checks. - Use encryption. In addition, Fortify Runtime provides security controls to be enforced every time the application performs a deserialization from an ObjectInputStream, protecting both application code but also library and framework code from this type of attack.

 Line of Code impacted
 public static Object deserialize(String path) throws IOException, ClassNotFoundException {
		ObjectInputStream objInpStream =  new ObjectInputStream(new FileInputStream(new File(path)));
		return objInpStream.readObject();
	}

 help me to resolve this iisue using Java 8
