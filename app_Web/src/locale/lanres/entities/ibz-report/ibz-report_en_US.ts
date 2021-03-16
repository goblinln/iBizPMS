import IbzReport_en_US_Base from './ibz-report_en_US_base';

function getLocaleResource(){
    const IbzReport_en_US_OwnData = {};
    const targetData = Object.assign(IbzReport_en_US_Base(), IbzReport_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;