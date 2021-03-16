import BugStats_en_US_Base from './bug-stats_en_US_base';

function getLocaleResource(){
    const BugStats_en_US_OwnData = {};
    const targetData = Object.assign(BugStats_en_US_Base(), BugStats_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;