const express = require('express');
const bodyParser = require('body-parser');
const xml2js = require('xml2js');
const { Buffer } = require('buffer');

const app = express();
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

const extractSAMLData = (samlResponse) => {
    return new Promise((resolve, reject) => {
        // Decode Base64 SAML Response
        const decodedXML = Buffer.from(samlResponse, 'base64').toString('utf-8');

        // Convert XML to JSON
        xml2js.parseString(decodedXML, { explicitArray: false }, (err, result) => {
            if (err) reject(err);
            else resolve(result);
        });
    });
};

// API Endpoint to Extract SAML Data
app.post('/saml/extract', async (req, res) => {
    try {
        const samlResponse = req.body.SAMLResponse;
        if (!samlResponse) {
            return res.status(400).json({ error: 'Missing SAMLResponse' });
        }

        const jsonData = await extractSAMLData(samlResponse);
        res.json(jsonData);
    } catch (error) {
        res.status(500).json({ error: 'Failed to parse SAML' });
    }
});

// Start Server
app.listen(3000, () => console.log("Server running on port 3000"));