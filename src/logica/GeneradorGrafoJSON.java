package logica;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class GeneradorGrafoJSON {
    private File archivo;
    private GrafoConPesosEnVertices grafo;

    public GeneradorGrafoJSON(File archivo) {
        this.archivo = archivo;
        JsonObject jsonObject = leerArchivoJSON();
        int cantidadVertices = obtenerCantidadVertices(jsonObject);
        this.grafo = new GrafoConPesosEnVertices(cantidadVertices);
        mapearVerticesJSON(jsonObject);
        mapearAristasJSON(jsonObject);
    }

    private JsonObject leerArchivoJSON() {
        try (FileReader reader = new FileReader(archivo)) {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            if (jsonObject == null) {
                throw new IllegalArgumentException("El archivo no contiene un objeto JSON válido: " + archivo);
            }
            return jsonObject;
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + archivo);
        } catch (JsonParseException e) {
            throw new IllegalArgumentException("El archivo no tiene un formato JSON válido: " + archivo);
        }
    }

    private int obtenerCantidadVertices(JsonObject jsonObject) {
        JsonArray vertices = obtenerJsonArray(jsonObject, "vertices");
        return vertices.size();
    }

    private void mapearVerticesJSON(JsonObject jsonObject) {
        JsonArray verticesArray = obtenerJsonArray(jsonObject, "vertices");
        for (JsonElement verticeElement : verticesArray) {
            JsonObject verticeJsonObject = obtenerJsonObject(verticeElement);
            double peso = obtenerCampoComoDouble(verticeJsonObject, "peso");
            int numeroVertice = obtenerCampoComoInt(verticeJsonObject, "numeroVertice");
            grafo.asignarPesoVertice(numeroVertice, peso);
        }
    }

    private void mapearAristasJSON(JsonObject jsonObject) {
        JsonArray aristasArray = obtenerJsonArray(jsonObject, "aristas");
        for (JsonElement aristaElement : aristasArray) {
            JsonObject aristaJsonObject = obtenerJsonObject(aristaElement);
            int vertice1 = obtenerCampoComoInt(aristaJsonObject, "vertice1");
            int vertice2 = obtenerCampoComoInt(aristaJsonObject, "vertice2");
            grafo.agregarArista(vertice1, vertice2);
        }
    }

    private JsonArray obtenerJsonArray(JsonObject jsonObject, String campo) {
        JsonArray jsonArray = jsonObject.getAsJsonArray(campo);
        if (jsonArray == null) {
            throw new IllegalArgumentException("El archivo JSON no contiene el campo '" + campo + "'.");
        }
        return jsonArray;
    }

    private JsonObject obtenerJsonObject(JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) {
            throw new IllegalArgumentException("El elemento no es un objeto JSON válido.");
        }
        return jsonElement.getAsJsonObject();
    }

    private double obtenerCampoComoDouble(JsonObject jsonObject, String campo) {
        if (!jsonObject.has(campo)) {
            throw new IllegalArgumentException("El campo '" + campo + "' no está presente.");
        }
        try {
            return jsonObject.get(campo).getAsDouble();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo '" + campo + "' no es un número válido.");
        }
    }

    private int obtenerCampoComoInt(JsonObject jsonObject, String campo) {
        if (!jsonObject.has(campo)) {
            throw new IllegalArgumentException("El campo '" + campo + "' no está presente.");
        }
        try {
            return jsonObject.get(campo).getAsInt();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo '" + campo + "' no es un entero válido.");
        }
    }

    public GrafoConPesosEnVertices obtenerGrafo() {
        return grafo;
    }
}
