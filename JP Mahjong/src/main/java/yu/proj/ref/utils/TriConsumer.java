package yu.proj.ref.utils;

import java.util.Objects;

/**  
 * @ClassName: TriConsumer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
@FunctionalInterface
public interface TriConsumer<T, U, W> {
    void accept(T t, U u, W w);

    default TriConsumer<T, U, W> andThen(TriConsumer<? super T, ? super U, ? super W> after) {
        Objects.requireNonNull(after);
        return (l, r, w) -> {
            accept(l, r, w);
            after.accept(l, r, w);
        };
    }
}
