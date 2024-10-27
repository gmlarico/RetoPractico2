package com.lite.Prueba.ms_ventas.infraestructure.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lite.Prueba.ms_ventas.infraestructure.database.ProductApplication;
import com.lite.Prueba.ms_ventas.infraestructure.database.ProductosDT;

//import org.springframework.transaction.annotation.Transactional;

@RestController
//@Transactional
@RequestMapping("/productos")
public class ProductoController {
    

    private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @Value("${app.producto.uno}")
    private String pd1;

    @Autowired
    private ProductApplication productApplication;

    @GetMapping("/")
    public List<ProductosDT> getProductos() {
        return productApplication.findAll();
    }

    @GetMapping("/{id}")
    public ProductosDT getProducto(@PathVariable Long id) {
        log.info("ID: " + id);  
        return productApplication.findById(id).orElse(null);
    }

    @PostMapping("/new")
    public ProductosDT addProducto(@RequestBody ProductosDT producto) {
        return productApplication.save(producto);
    }

    @PutMapping("/update/{id}")
    public ProductosDT updateProducto(@PathVariable Long id, @RequestBody ProductosDT producto) {
        ProductosDT productoDB = productApplication.findById(id).orElse(null);
        productoDB.setNombre(producto.getNombre());
        productoDB.setPrecio(producto.getPrecio());
        return productApplication.save(productoDB);
    }

    @PutMapping("/update")
    public ProductosDT updateProductoBody(@RequestBody ProductosDT producto) {
        ProductosDT productoDB = productApplication.findById(producto.getId()).orElse(null);
        productoDB.setNombre(producto.getNombre());
        productoDB.setPrecio(producto.getPrecio());
        return productApplication.save(productoDB);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducto(@PathVariable Long id) {
        ProductosDT productoDB = productApplication.findById(id).orElse(null);
        productApplication.delete(productoDB);
    }



    /*
    //Metodos que llaman a mi clase simple
    //una lista
    @GetMapping("/")
    public List<Productos> getProductos()  {
        Productos producto1 = new Productos(12, "Producto 1", 100.0);
        Productos producto2 = new Productos(12, "Producto 1", 100.0);
        Productos producto3 = new Productos(12, "Producto 1", 100.0);
        List<Productos> Producto = List.of(producto1,producto2,producto3);
        return Producto;
    }

    //una variable path
    @GetMapping("/{id}")
    public Productos getProducto(@PathVariable Long id)  {
        
        return new Productos(id.intValue(), "Producto 1", 100.2);
    }

    //una variable path
    @GetMapping("/buscar")
    public Productos getProductos(@RequestParam Long id,@RequestParam String nombre)  {
        
        return new Productos(id.intValue(), "Producto 1", 100.2);
    }*/
}
