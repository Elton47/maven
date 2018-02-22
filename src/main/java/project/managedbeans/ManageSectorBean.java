package project.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import project.dao.SectorDao;
import project.entity.Sector;

@ViewScoped
@ManagedBean(name = "manageSectorBean")
public class ManageSectorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private final SectorDao sectorDao = new SectorDao();
	private Sector sector = new Sector();
	private List<Sector> sectors;
	@PostConstruct
	public void init() {
		sectors = sectorDao.getSectors();
	}
	public List<Sector> getSectors() {
		return sectors;
	}
	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	public void addSector(String code, String name, String department, String employee) {
		if(code.length() > 0 && name.length() > 0 && department.length() > 0) { // Not employee because sector can remain without a manager (employee).
			sectorDao.addSector(code, name, department, employee);
			sectors = sectorDao.getSectors(); // Refresh.
		}
	}
	public void removeSector(String code) {
		sectorDao.removeSector(code);
		sectors = sectorDao.getSectors(); // Refresh.
	}
}
