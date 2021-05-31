import Bug_en_US_Base from './bug_en_US_base';

function getLocaleResource(){
    const Bug_en_US_OwnData = {};
    const targetData = Object.assign(Bug_en_US_Base(), Bug_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;