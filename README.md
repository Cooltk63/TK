Vulnerability : Insecure Randomness
Vulnerability Description in Detail : The random number generator implemented by random() cannot withstand a cryptographic attack.
Likely Impact: The random number generator implemented by random() cannot withstand a cryptographic attack.
Recommendation:When unpredictability is critical, as is the case with most security-sensitive uses of randomness, use a cryptographic PRNG. Regardless of the PRNG you choose, always use a value with sufficient entropy to seed the algorithm. (Do not use values such as the current time because it offers only negligible entropy.) The Java language provides a cryptographic PRNG in java.security.SecureRandom. As is the case with other algorithm-based classes in java.security, SecureRandom provides an implementation-independent wrapper around a particular set of algorithms. When you request an instance of a SecureRandom object using SecureRandom.getInstance(), you can request a specific implementation of the algorithm. If the algorithm is available, then it is given as a SecureRandom object. If it is unavailable or if you do not specify a particular implementation, then you are given a SecureRandom implementation selected by the system. Sun provides a single SecureRandom implementation with the Java distribution named SHA1PRNG, which Sun describes as computing: "The SHA-1 hash over a true-random seed value concatenated with a 64-bit counter which is incremented by 1 for each operation. From the 160-bit SHA-1 output, only 64 bits are used [1]." However, the specifics of the Sun implementation of the SHA1PRNG algorithm are poorly documented, and it is unclear what sources of entropy the implementation uses and therefore what amount of true randomness exists in its output. Although there is speculation on the Web about the Sun implementation, there is no evidence to contradict the claim that the algorithm is cryptographically strong and can be used safely in security-sensitive contexts.

Code Impacted :
public static String getAlphanum(int n)
    {
        /*String Alphanumeric="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                +"0123456789"+"abcdefghijklmnopqrstuvwxyz";*/
        String Alphanumeric= "0123456789";
        StringBuilder sb=new StringBuilder(n);
        for(int i=0;i<n;i++)
        {
            int index=(int)(Alphanumeric.length()*Math.random());
            sb.append(Alphanumeric.charAt(index));
        }
        return sb.toString();
    }

    Above method called as per below
    String referenceNumber = EISWebService.getAlphanum(25);

