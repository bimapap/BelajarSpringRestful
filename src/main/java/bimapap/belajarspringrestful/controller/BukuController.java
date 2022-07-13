package bimapap.belajarspringrestful.controller;

import bimapap.belajarspringrestful.model.Buku;
import bimapap.belajarspringrestful.repository.BukuRepository;
import bimapap.belajarspringrestful.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buku")
public class BukuController {

    @Autowired
//    BukuService service;
    BukuRepository bukuRepository;

    @GetMapping("/")
    public List<Buku> getAll(){
        return bukuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBookById(@PathVariable(value = "id") Long id){
        Buku buku = bukuRepository.findById(id).get();
        if (buku == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(buku);
    }

    @GetMapping("/sortbuku")
    public List<Buku> sortBook(@RequestParam(value ="title") String titleBook){
        return bukuRepository.findByTitleBook(titleBook);
    }

    @GetMapping("/sortstatus/{statusPeminjaman}")
    public List<Buku> sortStatus(@PathVariable(value = "statusPeminjaman") int statusPeminjaman){
        return bukuRepository.findByStatusPeminjaman(statusPeminjaman);
    }

    @PostMapping("/")
    public Buku addBook(@RequestBody Buku buku){
        return bukuRepository.save(buku);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBook(@PathVariable(value = "id")Long id, @Validated @RequestBody Buku detailBuku){
        Buku buku = bukuRepository.findById(id).get();
        if(buku == null)
            return ResponseEntity.notFound().build();

        buku.setTitleBook(detailBuku.getTitleBook());
        buku.setNamaDepanPengarang(detailBuku.getNamaDepanPengarang());
        buku.setNamaBelakangPengarang(detailBuku.getNamaBelakangPengarang());
        buku.setNamaPeminjam(detailBuku.getNamaPeminjam());
        buku.setStatusPeminjaman(detailBuku.getStatusPeminjaman());
        Buku updatedBuku = bukuRepository.save(buku);
        return ResponseEntity.ok(updatedBuku);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable (value = "id") Long id){
        Buku buku = bukuRepository.findById(id).get();
        String result = "";
        if (buku == null){
            result = "id " + id + " tidak ditemukan";
            return result;
        }
        result = "id " + id + " berhasil di delete";
        bukuRepository.deleteById(id);
        return result;
    }

}
