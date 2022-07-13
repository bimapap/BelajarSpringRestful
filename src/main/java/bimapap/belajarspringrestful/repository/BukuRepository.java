package bimapap.belajarspringrestful.repository;

import bimapap.belajarspringrestful.model.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BukuRepository extends JpaRepository<Buku,Long> {
    List<Buku> findByStatusPeminjaman (int statusPeminjaman);
    List<Buku> findByTitleBook(String titleBook);
}
