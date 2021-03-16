import IBZWEEKLY_en_US_Base from './ibzweekly_en_US_base';

function getLocaleResource(){
    const IBZWEEKLY_en_US_OwnData = {};
    const targetData = Object.assign(IBZWEEKLY_en_US_Base(), IBZWEEKLY_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;