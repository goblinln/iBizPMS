import CaseStats_en_US_Base from './case-stats_en_US_base';

function getLocaleResource(){
    const CaseStats_en_US_OwnData = {};
    const targetData = Object.assign(CaseStats_en_US_Base(), CaseStats_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;