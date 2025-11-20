package QUICKSORT;

import java.io.*;
import java.util.*;

public class Quicksort {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Leer archivo y regresar lista de enteros
    public List<Integer> fileToList(String fileName) {
        List<Integer> datos = new ArrayList<>();
        File file;
        FileReader reader;
        BufferedReader buffer;
        String linea;

        try {
            file = new File("C:\\archivos\\" + fileName);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            while ((linea = buffer.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");
                for (String p : partes) {
                    datos.add(Integer.parseInt(p));
                }
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.toString());
        }

        return datos;
    }

    // Guardar arreglo ordenado
    public void writeArrayToFile(String fileName, int[] array) {
        FileWriter file;
        PrintWriter writer;

        try {
            file = new FileWriter("C:\\archivos\\" + fileName);
            writer = new PrintWriter(file);

            for (int num : array) {
                writer.println(num);
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error al crear archivo: " + e.toString());
        }
    }

    // QuickSort
    public void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int pivoteIndex = particion(arr, inicio, fin);
            quickSort(arr, inicio, pivoteIndex - 1);
            quickSort(arr, pivoteIndex + 1, fin);
        }
    }

    // Partición
    public int particion(int[] arr, int inicio, int fin) {
        int pivote = arr[fin];
        int i = inicio - 1;

        for (int j = inicio; j < fin; j++) {
            if (arr[j] <= pivote) {
                i++;
                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = temp;

        return i + 1;
    }

    // Método principal del proceso
    public void ordenarArchivo() {
        try {
            System.out.print("Nombre del archivo a ordenar: ");
            String entrada = br.readLine();

            System.out.print("Nombre del archivo de salida: ");
            String salida = br.readLine();

            // Leer archivo
            List<Integer> lista = fileToList(entrada);
            int[] array = lista.stream().mapToInt(i -> i).toArray();

            // Ordenar
            quickSort(array, 0, array.length - 1);

            // Guardar
            writeArrayToFile(salida, array);

            System.out.println("Ordenamiento completado. Archivo guardado como: " + salida);

        } catch (IOException e) {
            System.out.println("Error al leer datos del usuario: " + e.toString());
        }
    }

    public static void main(String[] args) {
        Quicksort app = new Quicksort();
        app.ordenarArchivo();
    }
}
