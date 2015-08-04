package net.imspring.study.minidao.dao;
/**
 * 对象转换接口
 * @author Administrator
 *
 * @param <S> 源对象
 * @param <T> 目标对象
 */
public interface Convert<S,T> {
	public T getTarget(S source);
}
