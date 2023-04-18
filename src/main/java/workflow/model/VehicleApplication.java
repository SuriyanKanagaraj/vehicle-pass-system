package workflow.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "vehicleApplication")
public class VehicleApplication {

	@Id
	@Column(name = "vehicleNumber", unique = true, nullable = false)
    @NotEmpty(message = "*Please provide the Vehicle Number")
    private String vehicleNumber;

	@Column(name = "linkUsername", nullable = false)
    private String linkUsername;

	@Column(name = "applicationDate")
    private String applicationDate;

	@Column(name = "makeAndModel", nullable = false)
    @NotEmpty(message = "*Please provide the Make and Model of the vehicle")
    private String makeAndModel;

	@Column(name = "rcHolderName", nullable = false)
    @NotEmpty(message = "*Please provide the RC Holder Name")
    private String rcHolderName;

	@Column(name = "insuranceEndDate", nullable = false)
    @NotEmpty(message = "*Please provide the Insurance End Date")
    private String insuranceEndDate;

	@Column(name = "isVehicleOwnedByOthers")
    private Boolean isVehicleOwnedByOthers;

	@Column(name = "relationToTheStudent", nullable = true)
    private String relationToTheStudent;

	@Column(name = "mobileNumberOfTheRCHolder", nullable = true)
    private String mobileNumberOfTheRCHolder;

	@Column(name = "addressOfTheRCHolder", nullable = true)
    private String addressOfTheRCHolder;

	@Column(name = "expiryDate")
    private String expiryDate;

	@Column(name = "status")
    private String status;

	@Column(name = "stage")
    private Integer stage;

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getLinkUsername() {
		return linkUsername;
	}

	public void setLinkUsername(String linkUsername) {
		this.linkUsername = linkUsername;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getMakeAndModel() {
		return makeAndModel;
	}

	public void setMakeAndModel(String makeAndModel) {
		this.makeAndModel = makeAndModel;
	}

	public String getRcHolderName() {
		return rcHolderName;
	}

	public void setRcHolderName(String rcHolderName) {
		this.rcHolderName = rcHolderName;
	}

	public String getInsuranceEndDate() {
		return insuranceEndDate;
	}

	public void setInsuranceEndDate(String insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}

	public Boolean getIsVehicleOwnedByOthers() {
		return isVehicleOwnedByOthers;
	}

	public void setIsVehicleOwnedByOthers(Boolean isVehicleOwnedByOthers) {
		this.isVehicleOwnedByOthers = isVehicleOwnedByOthers;
	}

	public String getRelationToTheStudent() {
		return relationToTheStudent;
	}

	public void setRelationToTheStudent(String relationToTheStudent) {
		this.relationToTheStudent = relationToTheStudent;
	}

	public String getMobileNumberOfTheRCHolder() {
		return mobileNumberOfTheRCHolder;
	}

	public void setMobileNumberOfTheRCHolder(String mobileNumberOfTheRCHolder) {
		this.mobileNumberOfTheRCHolder = mobileNumberOfTheRCHolder;
	}

	public String getAddressOfTheRCHolder() {
		return addressOfTheRCHolder;
	}

	public void setAddressOfTheRCHolder(String addressOfTheRCHolder) {
		this.addressOfTheRCHolder = addressOfTheRCHolder;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

}
