package es.upm.fi.oeg.morph;


import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.query.Dataset;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import es.upm.fi.oeg.morph.relational.RelationalModel;
import es.upm.fi.oeg.morph.tc.DBManager;
import es.upm.fi.oeg.siq.tools.ParameterUtils;

/**
 * Demo for generating RDF from a relational Database through JDBC
 * @author jpc
 *
 */
public class DemoQueryJava {
  Config conf = ConfigFactory.load().getConfig("morph");
  RelationalModel relat;
  DBManager db;
  
  public DemoQueryJava(){
	db=new DBManager(conf.getString("jdbc.driver"),conf.getString("jdbc.source.url"),
			conf.getString("jdbc.source.user"),conf.getString("jdbc.source.password"),false);
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
	Morph morph=new Morph();
	Dataset ds=morph.generateJdbc(mappingFile);
    RDFDataMgr.write(System.out,ds.asDatasetGraph(),Lang.NQUADS);  
  }
  
  public static final void main(String[] args){
	DemoQueryJava dqj = new DemoQueryJava();	
	dqj.generate("data/d005/r2rmla.ttl");		    	    
  }
}
