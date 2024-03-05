package global.mix.sm.api.ejb;

import global.mix.sm.api.entity.Detallematerial;
import global.mix.sm.api.entity.Material;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rcacacho
 */
public interface MaterialBeanLocal {

    List<Material> listMaterialBuzon(String material, Date fechaInicio, Date fechaFin);

    List<Material> listMaterial();

    Material saveMaterial(Material material, String usuarioCreacion);

    Material updateMaterial(Material material, String usuarioModificacion);

    Material deleteMaterial(Material material, String usuarioEliminacion);

    Material findMaterialById(Integer idmaterial);

    Material findMaterialByNombre(String material);

    Detallematerial saveDetalleMaterial(Detallematerial detalleMaterial, String usuarioCreacion);
    
    List<Detallematerial> listDetalleMaterial(Integer idmaterial);

}
