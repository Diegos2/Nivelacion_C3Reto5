package restful.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import restful.Model.Conexion;
import restful.Model.PersonaModel;

/**
 *
 * @author Diegos
 */
public class PersonaService {
    
    public ArrayList<PersonaModel> getPersonas() {
        ArrayList<PersonaModel> lista = new ArrayList<>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM persona";
        try {
            Statement stm = conn.getCon().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                PersonaModel persona = new PersonaModel();
                persona.setIdentificacion(rs.getInt("identificacion"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setRol(rs.getString("rol"));
                persona.setEmail(rs.getString("email"));
                persona.setGenero(rs.getString("genero"));
                persona.setFechaCalculo(rs.getString("fechaCalculo"));
                persona.setPeso(rs.getFloat("peso"));
                persona.setEstatura(rs.getFloat("Estatura"));
                persona.setImc(rs.getFloat("imc"));
                lista.add(persona);
            }
        } catch (SQLException e) {
        }
        return lista;
    }

    public PersonaModel getPersona(int id) {
        PersonaModel persona = new PersonaModel();
        Conexion conex = new Conexion();
        String Sql = "SELECT * FROM persona WHERE identificacion = ?";
        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                persona.setIdentificacion(rs.getInt("identificacion"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setRol(rs.getString("rol"));
                persona.setEmail(rs.getString("email"));
                persona.setGenero(rs.getString("genero"));
                persona.setFechaCalculo(rs.getString("fechaCalculo"));
                persona.setPeso(rs.getFloat("peso"));
                persona.setEstatura(rs.getFloat("Estatura"));
                persona.setImc(rs.getFloat("imc"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return persona;
    }

    public PersonaModel addPersona(PersonaModel persona) {
        Conexion conex = new Conexion();
        String Sql = "INSERT INTO persona(identificacion, nombres, apellidos, rol, email, genero, fechaCalculo, peso, estatura, imc)";
        Sql = Sql + "values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = conex.getCon().prepareStatement(Sql);
            pstm.setInt(1, persona.getIdentificacion());
            pstm.setString(2, persona.getNombres());
            pstm.setString(3, persona.getApellidos());
            pstm.setString(4, persona.getRol());
            pstm.setString(5, persona.getEmail());
            pstm.setString(6, persona.getGenero());
            pstm.setString(7, persona.getFechaCalculo());
            pstm.setFloat(8, persona.getPeso());
            pstm.setFloat(9, persona.getEstatura());
            pstm.setFloat(10, persona.getImc());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return persona;
    }

    public PersonaModel updatePersona(PersonaModel persona) {
        Conexion conn = new Conexion();
        String sql = "UPDATE persona SET nombres=?, apellidos=?, rol=?, email=?, genero=?, fechaCalculo=?, peso=?, estatura=?, imc=? WHERE identificacion= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setString(1, persona.getNombres());
            pstm.setString(2, persona.getApellidos());
            pstm.setString(3, persona.getRol());
            pstm.setString(4, persona.getEmail());
            pstm.setString(5, persona.getGenero());
            pstm.setString(6, persona.getFechaCalculo());
            pstm.setFloat(7, persona.getPeso());
            pstm.setFloat(8, persona.getEstatura());
            pstm.setFloat(9, persona.getImc());
            pstm.setInt(10, persona.getIdentificacion());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al actualizar  " + excepcion.getMessage());
            return null;
        }
        return persona;
    }

    public String delPersona(int id) {
        Conexion conn = new Conexion();
        String sql = "DELETE FROM persona WHERE identificacion= ?";
        try {
            PreparedStatement pstm = conn.getCon().prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ha ocurrido un error al eliminar  " + excepcion.getMessage());
            return "{\"Accion\":\"Error\"}";
        }
        return "{\"Accion\":\"Registro Borrado\"}";
    }
}
