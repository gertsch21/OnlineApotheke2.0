package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="zugekauftes_produkt")
@PrimaryKeyJoinColumn(name="produkt_id")
public class ZugekauftesProdukt extends Produkt{
	private int menge_enthalten;
	private String hersteller;
	private String wirkstoff;
	private String wirkungsweise;
	private String anwendungsweise;
	
}
