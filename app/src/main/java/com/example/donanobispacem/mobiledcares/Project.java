package com.example.donanobispacem.mobiledcares;

import java.io.Serializable;

/**
 * Created by donanobispacem on 8/27/15.
 */
public class Project implements Serializable {

    private int _id;
    private String project_name;
    private String project_code;
    private String description;
    private String expected_outcome;
    private String end_user;
    private String updated_at;
    private String status;
    private String remarks;
    private String percent_accomplishment;
    private String percent_accomplishment_by;
    private String bidding_contractor;
    private String bidding_number;
    private String bidding_award;
    private String bidding_proceed;
    private String bidding_remarks;
    private String financial_budget;
    private String financial_contract_price;
    private String financial_actual_cost;
    private String financial_source;
    private String financial_variation;
    private String financial_remarks;
    private String timeline_target_start;
    private String timeline_target_end;
    private String timeline_actual_start;
    private String timeline_actual_end;
    private String timeline_duration;
    private String timeline_extension;
    private String timeline_remarks;

    public Project(int _id, String project_name, String project_code, String description, String expected_outcome, String end_user, String updated_at, String status, String remarks, String percent_accomplishment, String percent_accomplishment_by, String bidding_contractor, String bidding_number, String bidding_award, String bidding_proceed, String bidding_remarks, String financial_budget, String financial_contract_price, String financial_actual_cost, String financial_source, String financial_variation, String financial_remarks, String timeline_target_start, String timeline_target_end, String timeline_actual_start, String timeline_actual_end, String timeline_duration, String timeline_extension, String timeline_remarks ) {
        super();
        this._id = _id;
        this.project_name = project_name;
        this.project_code = project_code;
        this.description = description;
        this.expected_outcome = expected_outcome;
        this.end_user = end_user;
        this.updated_at = updated_at;
        this.status = status;
        this.remarks = remarks;
        this.percent_accomplishment = percent_accomplishment;
        this.percent_accomplishment_by = percent_accomplishment_by;
        this.bidding_contractor = bidding_contractor;
        this.bidding_number = bidding_number;
        this.bidding_award = bidding_award;
        this.bidding_proceed = bidding_proceed;
        this.bidding_remarks = bidding_remarks;
        this.financial_budget = financial_budget;
        this.financial_contract_price = financial_contract_price;
        this.financial_actual_cost = financial_actual_cost;
        this.financial_source = financial_source;
        this.financial_variation = financial_variation;
        this.financial_remarks = financial_remarks;
        this.timeline_target_start = timeline_target_start;
        this.timeline_target_end = timeline_target_end;
        this.timeline_actual_start = timeline_actual_start;
        this.timeline_actual_end = timeline_actual_end;
        this.timeline_duration = timeline_duration;
        this.timeline_extension = timeline_extension;
        this.timeline_remarks = timeline_remarks;
    }

    public int getID() { return _id; }
    public String getProjectName() { return project_name; }
    public String getProjectCode(){ return project_code; }
    public String getDescription() { return description; }
    public String getExpectedOutcome() { return expected_outcome; }
    public String getEndUser() { return end_user; }
    public String getUpdatedAt() { return updated_at; }
    public String getStatus() { return status; }
    public String getRemarks() { return remarks; }
    public String getPercentAccomplishment() { return percent_accomplishment; }
    public String getPercentAccomplishmentBy() { return percent_accomplishment_by; }
    public String getBiddingContractor() { return bidding_contractor; }
    public String getBiddingNumber() { return bidding_number; }
    public String getBiddingAward() { return bidding_award; }
    public String getBiddingProceed() { return bidding_proceed; }
    public String getBiddingRemarks() { return bidding_remarks; }
    public String getFinancialBudget() { return financial_budget; }
    public String getFinancialContractPrice() { return financial_contract_price; }
    public String getFinancialActualCost() { return financial_actual_cost; }
    public String getFinancialSource() { return financial_source; }
    public String getFinancialVariation() { return financial_variation; }
    public String getFinancialRemarks() { return financial_remarks; }
    public String getTimelineTargetStart() { return timeline_target_start; }
    public String getTimelineTargetEnd() { return timeline_target_end; }
    public String getTimelineActualStart() { return timeline_actual_start; }
    public String getTimelineActualEnd() { return timeline_actual_end; }
    public String getTimelineDuration() { return timeline_duration; }
    public String getTimelineExtension() { return timeline_extension; }
    public String getTimelineRemarks() { return timeline_remarks; }
 
    public void setID( int _id ) { this._id = _id; }
    public void setProjectName( String project_name ) { this.project_name = project_name; }
    public void setProjectCode( String project_code ){ this.project_code = project_code; }
    public void setDescription( String description ) { this.description = description; }
    public void setExpectedOutcome( String expected_outcome ) { this.expected_outcome = expected_outcome; }
    public void setEndUser( String end_user ) { this.end_user = end_user; }
    public void setUpdatedAt( String updated_at ) { this.updated_at = updated_at; }
    public void setStatus( String status ) { this.status = status; }
    public void setRemarks( String remarks ) { this.remarks = remarks; }
    public void setPercentAccomplishment( String percent_accomplishment ) { this.percent_accomplishment = percent_accomplishment; }
    public void setPercentAccomplishmentBy( String percent_accomplishment_by ) { this.percent_accomplishment_by = percent_accomplishment_by; }
    public void setBiddingContractor( String bidding_contractor ) { this.bidding_contractor = bidding_contractor; }
    public void setBiddingNumber( String bidding_number ) { this.bidding_number = bidding_number; }
    public void setBiddingAward( String bidding_award ) { this.bidding_award = bidding_award; }
    public void setBiddingProceed( String bidding_proceed ) { this.bidding_proceed = bidding_proceed; }
    public void setBiddingRemarks( String bidding_remarks ) { this.bidding_remarks = bidding_remarks; }
    public void setFinancialBudget( String financial_budget ) { this.financial_budget = financial_budget; }
    public void setFinancialContractPrice( String financial_contract_price ) { this.financial_contract_price = financial_contract_price; }
    public void setFinancialActualCost( String financial_actual_cost ) { this.financial_actual_cost = financial_actual_cost; }
    public void setFinancialSource( String financial_source ) { this.financial_source = financial_source; }
    public void setFinancialVariation( String financial_variation ) { this.financial_variation = financial_variation; }
    public void setFinancialRemarks( String financial_remarks ) { this.financial_remarks = financial_remarks; }
    public void setTimelineTargetStart( String timeline_target_start ) { this.timeline_target_start = timeline_target_start; }
    public void setTimelineTargetEnd( String timeline_target_end ) { this.timeline_target_end = timeline_target_end; }
    public void setTimelineActualStart( String timeline_actual_start ) { this.timeline_actual_start = timeline_actual_start; }
    public void setTimelineActualEnd( String timeline_actual_end ) { this.timeline_actual_end = timeline_actual_end; }
    public void setTimelineDuration( String timeline_duration ) { this.timeline_duration = timeline_duration; }
    public void setTimelineExtension( String timeline_extension ) { this.timeline_extension = timeline_extension; }
    public void setTimelineRemarks( String timeline_remarks ) { this.timeline_remarks = timeline_remarks; }
}