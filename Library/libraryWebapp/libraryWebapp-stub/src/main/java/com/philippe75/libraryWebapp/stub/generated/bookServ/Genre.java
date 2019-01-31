
package com.philippe75.libraryWebapp.stub.generated.bookServ;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour genre.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="genre">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TRAGEDY"/>
 *     &lt;enumeration value="SCIENCE_FICTION"/>
 *     &lt;enumeration value="FANTASY"/>
 *     &lt;enumeration value="MYTHOLOGY"/>
 *     &lt;enumeration value="ADVENTURE"/>
 *     &lt;enumeration value="MYSTERY"/>
 *     &lt;enumeration value="DRAMA"/>
 *     &lt;enumeration value="ROMANCE"/>
 *     &lt;enumeration value="SATIRE"/>
 *     &lt;enumeration value="HORROR"/>
 *     &lt;enumeration value="TRAGIC_COMEDY"/>
 *     &lt;enumeration value="YOUNG_ADULT_FICTION"/>
 *     &lt;enumeration value="DYSTOPIA"/>
 *     &lt;enumeration value="ACTION_THRILLER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "genre")
@XmlEnum
public enum Genre {

    TRAGEDY,
    SCIENCE_FICTION,
    FANTASY,
    MYTHOLOGY,
    ADVENTURE,
    MYSTERY,
    DRAMA,
    ROMANCE,
    SATIRE,
    HORROR,
    TRAGIC_COMEDY,
    YOUNG_ADULT_FICTION,
    DYSTOPIA,
    ACTION_THRILLER;

    public String value() {
        return name();
    }

    public static Genre fromValue(String v) {
        return valueOf(v);
    }

}
