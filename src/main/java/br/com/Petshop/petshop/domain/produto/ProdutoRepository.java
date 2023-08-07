package br.com.Petshop.petshop.domain.produto;


import br.com.Petshop.petshop.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    @Modifying
    @Query("DELETE FROM Produto p WHERE p.id = :produtoId")
    void deleteAssociationsByProdutoId(@Param("produtoId") Long produtoId);
}