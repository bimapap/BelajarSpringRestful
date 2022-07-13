package bimapap.belajarspringrestful.service;

import bimapap.belajarspringrestful.model.Buku;
import bimapap.belajarspringrestful.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BukuService {

    @Autowired
    BukuRepository bukuRepository;

    // CREATE
    public Buku createBuku(Buku emp) {
        return bukuRepository.save(emp);
    }

    // READ
    public List<Buku> getBuku() {
        return bukuRepository.findAll();
    }

    // DELETE
    public void deleteBuku(Long id) {
        bukuRepository.deleteById(id);
    }

}
