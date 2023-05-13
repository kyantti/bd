package es.unex.cum.bd.practicapareja.model.entities;

public class ProjectSubproject {
    private String projectShortDenomination;
    private String subprojectLongDenomination;

    public ProjectSubproject(String projectShortDenomination, String subprojectLongDenomination) {
        this.projectShortDenomination = projectShortDenomination;
        this.subprojectLongDenomination = subprojectLongDenomination;
    }

    public String getProjectShortDenomination() {
        return projectShortDenomination;
    }
    public void setProjectShortDenomination(String projectShortDenomination) {
        this.projectShortDenomination = projectShortDenomination;
    }
    public String getSubprojectLongDenomination() {
        return subprojectLongDenomination;
    }
    public void setSubprojectLongDenomination(String subprojectLongDenomination) {
        this.subprojectLongDenomination = subprojectLongDenomination;
    }
    
}
