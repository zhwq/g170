/*
* 进制转换，将 10 进制浮点数转换成 64 进制
* <p/>
* 1. 整数 小数拆成两部分
* 2. 转换成二进制 除2 余数为该位权上的数
* 3. 2^6 = 64, 6位合一
* <p/>
* 参考: https://blog.csdn.net/Umbrella_Um/article/details/102864634
*/
const R = 6
// 10进制转二进制
const tob = (b, source) => {
    // console.log("开始 b = " + b)
    const s = Math.floor(source / 2)
    const t = source % 2
    // 更新b
    b = t + '' + b
    // console.log("循环 b = " + b)
    if (s !== 0) {
        b = tob(b, s)
    }
    return b
}
// 左补0
const leftPadding = (source, padding) => {
    let i = padding
    while(i-- > 0) {
        source = '0' + source
    }
    return source
}
// 分组带权求值
const cal = (rerseArr) => {
    // console.log(rerseArr)
    // arr.reduce(callback(accumulator, currentValue[, index[, array]])[, initialValue])
    return rerseArr.reduce((a, c, i) => a * 1 + Math.pow(2, i) * c)
}

// 二进制字符串转64进制
const bto64 = (b) => {
    // 字符数组翻转
    // 按固定长度6分组
    // 分组求值
    const a = b.split('').reverse()
    let i = 0
    let result = ''
    for(;i < a.length;) {
        result = cal(a.slice(i, i + R)) + result
        i += R
    }
    return result
}

// 导出的函数
const oto64 = (input) => {
    const inputArr = input.split('.')

    const input1 = inputArr[0]
    const input2 = inputArr[1]

    const b1 = tob('', input1)
    const b2 = tob('', input2)

    // 2^6 = 64
    const zeroPadding1 = (R - b1.length % R) % R
    const zeroPadding2 = (R - b2.length % R) % R


    const pb1 = leftPadding(b1, zeroPadding1)
    const pb2 = leftPadding(b2, zeroPadding2)

    return bto64(pb1) + '.' + bto64(pb2)
}

const input = '128.57'

console.log(`10进制数: ${input} 转换成64进制数据: ${oto64(input)}`)

