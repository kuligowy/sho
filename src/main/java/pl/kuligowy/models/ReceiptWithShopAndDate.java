/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.kuligowy.models;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.rest.core.config.Projection;

/**
 *
 * @author coolig
 */
@Projection(name = "receiptWithShopAndDate",types = Receipt.class)
public interface ReceiptWithShopAndDate {
    BigDecimal getTotal();
    Date getEventTime();
    Shop getShop();
    
    
}
