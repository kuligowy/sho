package pl.kuligowy.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne()
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @Transient
    private BigDecimal total;
    @Column(name = "event_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date eventTime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private List<ReceiptItem> receiptItems;

    public Receipt() {
    }

    public Receipt(Shop shopId, Date eventTime) {
        this.shop = shopId;
        this.eventTime = eventTime;
        receiptItems = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shopId) {
        this.shop = shopId;
    }

    public BigDecimal getTotal() {
//        double result = receiptItems
//                .stream()
//                .mapToDouble(ri -> ri.getPrice().doubleValue() * ri.getQuantity())
//                .reduce(0, (a, b) -> a + b);
//        return new BigDecimal(result);

//           liite different
        BigDecimal result2 = BigDecimal.ZERO;
        if(receiptItems!=null){
            result2= receiptItems
                .stream()
                .map((t) -> t.getPrice().multiply(BigDecimal.valueOf(t.getQuantity())))
                .reduce(BigDecimal.ZERO, (t, u) -> t.add(u));
        }
        return result2;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public List<ReceiptItem> getReceiptItems() {
        return receiptItems;
    }

    public void setReceiptItems(List<ReceiptItem> receiptItems) {
        this.receiptItems = receiptItems;
    }

}
