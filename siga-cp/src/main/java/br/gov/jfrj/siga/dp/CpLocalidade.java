/*******************************************************************************
 * Copyright (c) 2006 - 2011 SJRJ.
 * 
 *     This file is part of SIGA.
 * 
 *     SIGA is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     SIGA is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with SIGA.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
/*
 * Criado em  21/12/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.gov.jfrj.siga.dp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.gov.jfrj.siga.dp.dao.CpDao;
import br.gov.jfrj.siga.model.Selecionavel;

@Entity
@Table(name = "corporativo.cp_localidade")
@SuppressWarnings("serial")
@Cache(region = CpDao.CACHE_CORPORATIVO, usage = CacheConcurrencyStrategy.READ_ONLY)
public class CpLocalidade extends AbstractCpLocalidade implements Serializable,
		Selecionavel {

	private static List<String> municipios = null;
	
	public static List<String> obterMunicipios() {
		ArrayList<String> municipios = new ArrayList<String>();
		municipios.add("SAPUCAIA");
		municipios.add("BELÉM");
		municipios.add("SAO JOAO DA PONTA");
		municipios.add("ALENQUER");
		municipios.add("ALMEIRIM");
		municipios.add("ALTAMIRA");
		municipios.add("ANANINDEUA");
		municipios.add("BAIAO");
		municipios.add("BARCARENA");
		municipios.add("BRAGANCA");
		municipios.add("=BREVES");
		municipios.add("CACHOEIRA DO ARARI");
		municipios.add("CAMETA");
		municipios.add("CAPANEMA");
		municipios.add("CAPITAO POCO");
		municipios.add("CASTANHAL");
		municipios.add("CHAVES");
		municipios.add("CONCEICAO DO ARAGUAIA");
		municipios.add("CURIONOPOLIS");
		municipios.add("CURUCA");
		municipios.add("GURUPA");
		municipios.add("IGARAPE-ACU");
		municipios.add("IGARAPE-MIRI");
		municipios.add("IRITUIA");
		municipios.add("ITAITUBA");
		municipios.add("ITUPIRANGA");
		municipios.add("JACUNDA");
		municipios.add("MAE DO RIO");
		municipios.add("MARABA");
		municipios.add("MARACANA");
		municipios.add("MARAPANIM");
		municipios.add("MOJU");
		municipios.add("MONTE ALEGRE");
		municipios.add("MUANA");
		municipios.add("NOVA TIMBOTEUA");
		municipios.add("OBIDOS");
		municipios.add("OEIRAS DO PARA");
		municipios.add("ORIXIMINA");
		municipios.add("OUREM");
		municipios.add("PARAGOMINAS");
		municipios.add("PARAUAPEBAS");
		municipios.add("PEIXE-BOI");
		municipios.add("PONTA DE PEDRAS");
		municipios.add("PORTEL");
		municipios.add("PRIMAVERA");
		municipios.add("REDENCAO");
		municipios.add("RONDON DO PARA");
		municipios.add("RIO MARIA");
		municipios.add("SALINOPOLIS");
		municipios.add("SANTA IZABEL DO PARA");
		municipios.add("SANTANA DO ARAGUAIA");
		municipios.add("SANTAREM");
		municipios.add("SAO DOMINGOS DO CAPIM");
		municipios.add("SAO FELIX DO XINGU");
		municipios.add("SAO JOAO DO ARAGUAIA");
		municipios.add("SAO MIGUEL DO GUAMA");
		municipios.add("SAO SEBASTIAO DA BOA VISTA");
		municipios.add("SANTA MARIA DO PARA");
		municipios.add("SENADOR JOSE PORFIRIO");
		municipios.add("SOURE");
		municipios.add("TOME ACU");
		municipios.add("TUCURUI");
		municipios.add("TUCUMA");
		municipios.add("VIGIA");
		municipios.add("VISEU");
		municipios.add("XINGUARA");
		municipios.add("URUARA");
		municipios.add("MOCAJUBA");
		municipios.add("AUGUSTO CORREA");
		municipios.add("PACAJA");
		municipios.add("ABAETETUBA");
		municipios.add("BRASIL NOVO");
		municipios.add("MEDICILANDIA");
		municipios.add("RUROPOLIS");
		municipios.add("TAILANDIA");
		municipios.add("PORTO DE MOZ");
		municipios.add("ACARA");
		municipios.add("ANAJAS");
		municipios.add("TERMO JUDICIARIO DE AVEIRO");
		municipios.add("TERMO JUDICIARIO DE BAGRE");
		municipios.add("BONITO");
		municipios.add("BUJARU");
		municipios.add("TERMO JUDICIARIO DE COLARES");
		municipios.add("CURRALINHO");
		municipios.add("FARO");
		municipios.add("TERMO JUDICIARIO DE INHANGAPI");
		municipios.add("TERMO JUDICIARIO DE JURUTI");
		municipios.add("LIMOEIRO DO AJURU");
		municipios.add("TERMO JUDICIARIO DE MAGALHAES BARATA");
		municipios.add("MELGACO");
		municipios.add("PRAINHA");
		municipios.add("SALVATERRA");
		municipios.add("TERMO JUDICIARIO DE SANTA CRUZ DO ARARI");
		municipios.add("TERMO JUDICIARIO DE SANTAREM NOVO");
		municipios.add("SANTO ANTONIO DO TAUA");
		municipios.add("SAO CAETANO DE ODIVELAS");
		municipios.add("SAO FRANCISCO DO PARA");
		municipios.add("BENEVIDES");
		municipios.add("ABEL FIGUEIREDO");
		municipios.add("AGUA AZUL DO NORTE");
		municipios.add("AURORA DO PARA");
		municipios.add("BANNACH");
		municipios.add("BOM JESUS DO TOCANTINS");
		municipios.add("BREJO GRANDE DO ARAGUAIA");
		municipios.add("BREU BRANCO");
		municipios.add("CONCORDIA DO PARA");
		municipios.add("CUMARU DO NORTE");
		municipios.add("DOM ELISEU");
		municipios.add("ELDORADO DOS CARAJAS");
		municipios.add("GARRAFAO DO NORTE");
		municipios.add("GOIANESIA DO PARA");
		municipios.add("IPIXUNA DO PARA");
		municipios.add("JACAREACANGA");
		municipios.add("NOVA ESPERANCA DO PIRIA");
		municipios.add("NOVA IPIXUNA");
		municipios.add("NOVO PROGRESSO");
		municipios.add("OURILANDIA DO NORTE");
		municipios.add("PALESTINA DO PARA");
		municipios.add("PAU D ARCO");
		municipios.add("PLACAS");
		municipios.add("SANTA BARBARA DO PARA");
		municipios.add("SANTA LUZIA DO PARA");
		municipios.add("SANTA MARIA DAS BARREIRAS");
		municipios.add("NOVO REPARTIMENTO");
		municipios.add("SAO DOMINGOS DO ARAGUAIA");
		municipios.add("SAO GERALDO DO ARAGUAIA");
		municipios.add("SAO JOAO DE PIRABAS");
		municipios.add("TERRA ALTA");
		municipios.add("TERRA SANTA");
		municipios.add("TRAIRAO");
		municipios.add("ULIANOPOLIS");
		municipios.add("VITORIA DO XINGU");
		municipios.add("FLORESTA DO ARAGUAIA");
		municipios.add("MARITUBA");
		municipios.add("TRACUATEUA");
		municipios.add("QUATIPURU");
		municipios.add("CANAA DOS CARAJAS");
		municipios.add("CURUA");
		municipios.add("ANAPU");
		municipios.add("BELTERRA");
		municipios.add("CACHOEIRA DO PIRIA");
		municipios.add("PICARRA");
		
		return municipios;
	}

	public String getDescricao() {
		return getNmLocalidade();
	}

	public Long getId() {
		return new Long(getIdLocalidade());
	}

	public String getSigla() {
		return getNmLocalidade();
	}

	public void setSigla(String sigla) {
		setNmLocalidade(sigla);

	}

	public static List<String> getMunicipios() {
		if (municipios == null) 
			municipios = obterMunicipios();
		return municipios;
	}

	public static void setMunicipios(List<String> municipios) {
		CpLocalidade.municipios = municipios;
	}
	
}
