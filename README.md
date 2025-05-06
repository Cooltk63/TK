/**
 * A servlet filter that reads a JSON payload from the incoming HTTP request,
 * parses it into a Map<String, Object>, and stores the map as a request attribute.
 * 
 * This allows downstream components such as controllers to access the parsed JSON
 * without needing to re-read or re-parse the request body.
 *
 * Key functionality:
 * - Wraps the original HttpServletRequest to enable multiple reads of the input stream.
 * - Parses JSON request body using Jackson's ObjectMapper.
 * - Stores the resulting Map in request attributes under the key "data".
 *
 * This filter is especially useful in cases where request logging, validation,
 * or preprocessing is needed at a global level before reaching controllers.
 * 
 * Author: Your Name
 * Date: 2025-05-06
 */
public class JsonBodyFilter implements Filter {