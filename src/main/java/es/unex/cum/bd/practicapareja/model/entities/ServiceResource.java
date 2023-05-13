package es.unex.cum.bd.practicapareja.model.entities;

public class ServiceResource {
    private int serviceId;
    private String serviceDenomination;
    private int numberOfResources;
    
    public ServiceResource(int serviceId, String serviceDenomination, int numberOfResources) {
        this.serviceId = serviceId;
        this.serviceDenomination = serviceDenomination;
        this.numberOfResources = numberOfResources;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceDenomination() {
        return serviceDenomination;
    }

    public void setServiceDenomination(String serviceDenomination) {
        this.serviceDenomination = serviceDenomination;
    }

    public int getNumberOfResources() {
        return numberOfResources;
    }

    public void setNumberOfResources(int numberOfResources) {
        this.numberOfResources = numberOfResources;
    }
    
}
