package es.upm.fi.oeg.morph;

import java.util.Properties;

import org.apache.jena.riot.RiotWriter;

import com.hp.hpl.jena.query.Dataset;

import es.upm.fi.oeg.morph.execute.RdfGenerator;
import es.upm.fi.oeg.morph.r2rml.R2rmlReader;
import es.upm.fi.oeg.morph.relational.JDBCRelationalModel;
import es.upm.fi.oeg.morph.relational.RelationalModel;
import es.upm.fi.oeg.morph.tc.DBManager;
import es.upm.fi.oeg.siq.tools.ParameterUtils;

/**
 * Demo for generating RDF from a relational Database through JDBC
 * @author jpc
 *
 */
public class DemoQueryJava {
  Properties props=ParameterUtils.load(this.getClass().getResourceAsStream("/conf/config.properties"));
  RelationalModel relat;
  DBManager db;
  
  DemoQueryJava(){
	relat=new JDBCRelationalModel(props);
	db=new DBManager(props.getProperty("jdbc.driver"),props.getProperty("jdbc.source.url"),
		      props.getProperty("jdbc.source.user"),props.getProperty("jdbc.source.password"),false);
  }
  
  /**
   * Create the database using a script.
   * This is not necessary if you already have your database. By default this test uses an in-memory HSLQDB instance.
   */
  private void createDB(){
	String script = ParameterUtils.loadQuery("data/d005/create.sql");
    db.clearDB();
    db.createDB(script);
  }
  
  /**
   * Generate RDF using the provided R2rml mapping
   * Produces an RDF Dataset
   * @param mappingFile R2RML mapping file location
   */
  public void generate(String mappingFile){
	createDB();  
	R2rmlReader reader=new R2rmlReader(mappingFile);    
	RdfGenerator g=new RdfGenerator(reader,relat);
	Dataset ds=g.generate();
    RiotWriter.writeNQuads(System.out,ds.asDatasetGraph());  
  }
  
  public static final void main(String[] args){
	DemoQueryJava dqj = new DemoQueryJava();
	dqj.generate("data/d005/r2rmla.ttl");		    	    
  }
}
