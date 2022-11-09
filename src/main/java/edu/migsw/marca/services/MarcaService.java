package edu.migsw.marca.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import org.springframework.beans.factory.annotation.Autowired;
import edu.migsw.marca.entities.MarcaEntity;
import edu.migsw.marca.repositories.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcasRepository;

    public List<MarcaEntity> getAll() {
        return marcasRepository.findAll();
    }

    public List<MarcaEntity> getByRut(String rut) {
        return marcasRepository.findByRut(rut);
    }

    public Optional<MarcaEntity> getMarcaById(Long id) {
        return marcasRepository.findById(id);
    }
    public MarcaEntity save(MarcaEntity marca) {
        MarcaEntity marcaNueva=marcasRepository.save(marca);
        return marcaNueva;
    }

    public MarcaEntity subirMarca(String data) throws ParseException {
        String[] datoEntrada = data.split(";");
        String[] horaMinuto = datoEntrada[1].split(":");
        final String viejoFormato = "yyyy/mm/dd";
        final String nuevoFormato = "yyyy-mm-dd";
        SimpleDateFormat format = new SimpleDateFormat(viejoFormato);
        Date fecha = format.parse(datoEntrada[0]);
        format.applyPattern(nuevoFormato);
        String nuevaFecha = format.format(fecha);
        return new MarcaEntity(null, nuevaFecha, horaMinuto[0], horaMinuto[1], datoEntrada[2]);
    }

     public void obtenerMarcas() throws IOException, ParseException {
        FileReader fr = new FileReader("/data.txt");
        BufferedReader br = new BufferedReader(fr);
            String i;
            while ((i = br.readLine()) != null) {
                marcasRepository.save(subirMarca(i));
            }
            br.close();
            fr.close();
    }

    String msg ="Archivo guardado";
	String error="No se pudo guardar el archivo";
	public String saveData(MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
				byte [] bytes= file.getBytes();
				String folder= "/";
				Path path = Paths.get( folder+file.getOriginalFilename() );
				Files.write(path, bytes);				
				
		}
		return msg;
	} 
}
