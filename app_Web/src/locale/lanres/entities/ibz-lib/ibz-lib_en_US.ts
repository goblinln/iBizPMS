import IbzLib_en_US_Base from './ibz-lib_en_US_base';

function getLocaleResource(){
    const IbzLib_en_US_OwnData = {};
    const targetData = Object.assign(IbzLib_en_US_Base(), IbzLib_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;