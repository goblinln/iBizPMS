import ProductTeam_zh_CN_Base from './product-team_zh_CN_base';

function getLocaleResource(){
    const ProductTeam_zh_CN_OwnData = {};
    const targetData = Object.assign(ProductTeam_zh_CN_Base(), ProductTeam_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;