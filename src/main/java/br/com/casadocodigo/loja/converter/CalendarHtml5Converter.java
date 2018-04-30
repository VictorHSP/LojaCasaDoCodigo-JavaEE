package br.com.casadocodigo.loja.converter;

import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "br.com.casadocodigo.loja.converter.CalendarConverter", forClass=Calendar.class)
public class CalendarHtml5Converter implements Converter{

	private static DateTimeConverter originalCoverter = 
			new DateTimeConverter();
	
	static {
		originalCoverter.setPattern("yyyy-MM-dd");
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Date date = (Date) originalCoverter
				.getAsObject(context, component, value);
		
		if (date == null) {
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value == null) {
			return null;
		}
		
		Calendar calendar = (Calendar) value;
		
		return originalCoverter.getAsString(context, component, calendar.getTime());
	}
	
}
