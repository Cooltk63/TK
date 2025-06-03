$scope.downloadMocFile = function () {
    $http({
        method: 'POST',
        url: '/yourApp/download/moc', // Update with correct context path
        responseType: 'arraybuffer'
    }).then(function (response) {
        var blob = new Blob([response.data], { type: 'text/csv' });
        var downloadUrl = window.URL.createObjectURL(blob);

        var a = document.createElement('a');
        a.href = downloadUrl;
        a.download = 'moc.csv';
        document.body.appendChild(a);
        a.click();

        // Clean up
        document.body.removeChild(a);
        window.URL.revokeObjectURL(downloadUrl);
    }, function (error) {
        console.error("Download failed", error);
        alert("Failed to download the file.");
    });
};

xxxx

<button ng-click="downloadMocFile()" class="btn btn-warning">
    <i class="fa fa-download"></i> Download Sample File
</button>

xxxx


@PostMapping("/download/moc")
public void downloadMocFile(HttpServletResponse response) throws IOException {
    Path path = Paths.get(servletContext.getRealPath("/resources/document/moc.csv")).toAbsolutePath();
    Resource resource = new UrlResource(path.toUri());

    if (!resource.exists()) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
        return;
    }

    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=\"moc.csv\"");
    StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
}

