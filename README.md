@Service
public class TableValidationService {

    @Autowired
    private DataSource dataSource;

    public boolean isTableValid(String tableName) {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getTables(null, conn.getSchema(), tableName.toUpperCase(), new String[]{"TABLE"});
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public Set<String> getAllColumns(String tableName) {
        Set<String> columns = new HashSet<>();
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getColumns(null, conn.getSchema(), tableName.toUpperCase(), null);
            while (rs.next()) {
                columns.add(rs.getString("COLUMN_NAME").toUpperCase());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // or log.error
        }
        return columns;
    }

    public boolean areColumnsValid(String tableName, List<String> requiredColumns) {
        Set<String> tableColumns = getAllColumns(tableName);
        for (String col : requiredColumns) {
            if (!tableColumns.contains(col.toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}