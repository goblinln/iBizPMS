import ProductBranch_en_US_Base from './product-branch_en_US_base';

function getLocaleResource(){
    const ProductBranch_en_US_OwnData = {};
    const targetData = Object.assign(ProductBranch_en_US_Base(), ProductBranch_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;