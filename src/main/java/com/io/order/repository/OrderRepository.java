package com.io.order.repository;

import com.io.order.model.entity.OrderEntity;
import com.io.order.model.internal.OrderInternal;
import com.io.order.model.internal.ProductInternal;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    default void ifExistsByIdThenThrow(String id){
        if (this.existsById(id)){
            throw new DuplicateKeyException("Já existe um pedido com esse id");
        }
    }

    default OrderEntity saveIfNotExists(OrderEntity orderEntity){
        this.ifExistsByIdThenThrow(orderEntity.getOrderId());
        return this.save(orderEntity);
    }

    @Query(value = "SELECT o.pk_order_id AS orderId, " +
            "o.st_client_id AS clientId, " +
            "o.dt_order_date AS orderDate " +
            "FROM tb_orders o " +
            "WHERE o.pk_order_id = :orderId", nativeQuery = true)
    Optional<OrderInternal> findOrderDetailsByOrderId(@Param("orderId") String orderId);


    @Query(value = "SELECT " +
            "p.PK_PRODUCT_ID AS productId, " +
            "p.ST_NAME AS name, " +
            "p.DB_PRICE AS price, " +
            "op.IT_QUANTITY AS quantity " +
            "FROM TB_ORDERS o " +
            "JOIN TB_ORDER_PRODUCTS op ON o.PK_ORDER_ID = op.FK_ORDER_ID " +
            "JOIN TB_PRODUCTS p ON op.FK_PRODUCT_ID = p.PK_PRODUCT_ID " +
            "WHERE o.PK_ORDER_ID = :orderId", nativeQuery = true)
    Optional<List<ProductInternal>> findProductsByOrderId(@Param("orderId") String orderId);

}
