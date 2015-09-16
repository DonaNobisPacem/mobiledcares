package com.example.donanobispacem.mobiledcares;

import android.content.Context;
import android.content.pm.ComponentInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by donanobispacem on 9/3/15.
 */
public class ProjectTabsAdapter extends FragmentPagerAdapter {

    private static final String TAG_PROJECT_NAME = "project_name";
    private static final String TAG_PROJECT_CODE = "project_code";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_EXPECTED_OUTCOME = "expected_outcome";
    private static final String TAG_END_USER = "end_user";
    private static final String TAG_UPDATED_AT = "updated_at";
    private static final String TAG_COMPLETED_BY = "completed_by";
    private static final String TAG_STATUS = "status";
    private static final String TAG_CLASSIFICATION = "classification";
    private static final String TAG_REMARKS = "remarks";
    private static final String TAG_PERCENT_ACCOMPLISHMENT = "percent_accomplishment";
    private static final String TAG_PERCENT_ACCOMPLISHMENT_BY = "percent_accomplishment_by";
    private static final String TAG_BIDDING_CONTRACTOR = "bidding_contractor";
    private static final String TAG_BIDDING_NUMBER = "bidding_number";
    private static final String TAG_BIDDING_AWARD = "bidding_award";
    private static final String TAG_BIDDING_PROCEED = "bidding_proceed";
    private static final String TAG_BIDDING_REMARKS = "bidding_remarks";
    private static final String TAG_FINANCIAL_BUDGET = "financial_budget";
    private static final String TAG_FINANCIAL_CONTRACT_PRICE = "financial_contract_price";
    private static final String TAG_FINANCIAL_ACTUAL_COST = "financial_actual_cost";
    private static final String TAG_FINANCIAL_SOURCE = "financial_source";
    private static final String TAG_FINANCIAL_VARIATION = "financial_variation";
    private static final String TAG_FINANCIAL_REMARKS = "financial_remarks";
    private static final String TAG_TIMELINE_TARGET_START = "timeline_target_start";
    private static final String TAG_TIMELINE_TARGET_END = "timeline_target_end";
    private static final String TAG_TIMELINE_ACTUAL_START = "timeline_actual_start";
    private static final String TAG_TIMELINE_ACTUAL_END = "timeline_actual_end";
    private static final String TAG_TIMELINE_DURATION = "timeline_duration";
    private static final String TAG_TIMELINE_EXTENSION = "timeline_extension";
    private static final String TAG_TIMELINE_REMARKS = "timeline_remarks";
    private static final String TAG_FUND_SOURCES = "fund_sources";
    private static final String TAG_PROJECT_COMPONENTS = "project_components";
    private static final String TAG_PROJECT_PHASES = "project_phases";

    final int PAGE_COUNT = 7;
    private String tabTitles[] = new String[] { "Project Status", "General Information", "Bidding Details", "Financial Details", "Timeline Details", "Project Components", "Project Phases" };
    private Context context;
    private Project project;

    public ProjectTabsAdapter( Project project, FragmentManager fm, Context context ) {
        super(fm);
        this.context = context;
        this.project = project;
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                Bundle statArgs = new Bundle();
                statArgs.putString(TAG_STATUS, project.getStatus());
                statArgs.putString(TAG_CLASSIFICATION, project.getClassification());
                statArgs.putString(TAG_UPDATED_AT, project.getUpdatedAt());
                statArgs.putString(TAG_COMPLETED_BY, project.getCompletedBy());
                statArgs.putString(TAG_PERCENT_ACCOMPLISHMENT, project.getPercentAccomplishment());
                statArgs.putString(TAG_PERCENT_ACCOMPLISHMENT_BY, project.getPercentAccomplishmentBy());

                StatusFragment statFragment = new StatusFragment();
                statFragment.setArguments( statArgs );
                return statFragment;
            case 1:
                Bundle genArgs = new Bundle();
                genArgs.putString(TAG_PROJECT_CODE, project.getProjectCode());
                genArgs.putString(TAG_PROJECT_NAME, project.getProjectName());
                genArgs.putString(TAG_DESCRIPTION, project.getDescription());
                genArgs.putString(TAG_EXPECTED_OUTCOME, project.getExpectedOutcome());
                genArgs.putString(TAG_END_USER, project.getEndUser());
                genArgs.putString(TAG_REMARKS, project.getRemarks());

                GeneralFragment genFragment = new GeneralFragment();
                genFragment.setArguments( genArgs );
                return genFragment;
            case 2:
                Bundle bidArgs = new Bundle();
                bidArgs.putString(TAG_BIDDING_CONTRACTOR, project.getBiddingContractor());
                bidArgs.putString(TAG_BIDDING_NUMBER, project.getBiddingNumber());
                bidArgs.putString(TAG_BIDDING_AWARD, project.getBiddingAward());
                bidArgs.putString(TAG_BIDDING_PROCEED, project.getBiddingProceed());
                bidArgs.putString(TAG_BIDDING_REMARKS, project.getBiddingRemarks());

                BiddingFragment bidFragment = new BiddingFragment();
                bidFragment.setArguments( bidArgs );
                return bidFragment;
            case 3:
                Bundle finArgs = new Bundle();
                finArgs.putString(TAG_FINANCIAL_BUDGET, project.getFinancialBudget());
                finArgs.putString(TAG_FINANCIAL_CONTRACT_PRICE, project.getFinancialContractPrice());
                finArgs.putString(TAG_FINANCIAL_ACTUAL_COST, project.getFinancialActualCost());
                finArgs.putString(TAG_FINANCIAL_SOURCE, project.getFinancialSource());
                finArgs.putString(TAG_FINANCIAL_VARIATION, project.getFinancialVariation());
                finArgs.putString(TAG_FINANCIAL_REMARKS, project.getFinancialRemarks());
                finArgs.putStringArrayList(TAG_FUND_SOURCES, project.getFundSource());

                FinancialFragment finFragment = new FinancialFragment();
                finFragment.setArguments( finArgs );
                return finFragment;
            case 4:
                Bundle timeArgs = new Bundle();
                timeArgs.putString(TAG_TIMELINE_TARGET_START, project.getTimelineTargetStart());
                timeArgs.putString(TAG_TIMELINE_TARGET_END, project.getTimelineTargetEnd());
                timeArgs.putString(TAG_TIMELINE_ACTUAL_START, project.getTimelineActualStart());
                timeArgs.putString(TAG_TIMELINE_ACTUAL_END, project.getTimelineActualEnd());
                timeArgs.putString(TAG_TIMELINE_DURATION, project.getTimelineDuration());
                timeArgs.putString(TAG_TIMELINE_EXTENSION, project.getTimelineExtension());
                timeArgs.putString(TAG_TIMELINE_REMARKS, project.getTimelineRemarks());

                TimelineFragment timeFragment = new TimelineFragment();
                timeFragment.setArguments( timeArgs );
                return timeFragment;
            case 5:
                Bundle compArgs = new Bundle();
                compArgs.putStringArrayList(TAG_PROJECT_COMPONENTS, project.getComponents());

                ComponentFragment compFragment = new ComponentFragment();
                compFragment.setArguments( compArgs );
                return compFragment;
            case 6:
                Bundle phaseArgs = new Bundle();
                phaseArgs.putStringArrayList(TAG_PROJECT_PHASES, project.getPhases());

                PhaseFragment phaseFragment = new PhaseFragment();
                phaseFragment.setArguments( phaseArgs );
                return phaseFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
