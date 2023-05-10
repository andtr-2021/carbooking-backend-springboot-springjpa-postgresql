package com.example.backend.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.VoucherEntity;
import com.example.backend.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")
public class VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    @GetMapping("/vouchers")
    public List<VoucherEntity> getAllVouchers(){
        return voucherRepository.findAll();
    }

    @PostMapping("/vouchers")
    public VoucherEntity createVoucher(@RequestBody VoucherEntity voucher) {
        return voucherRepository.save(voucher);
    }

    // get employee by id rest api
    @GetMapping("/vouchers/{id}")
    public ResponseEntity<VoucherEntity> getVoucherById(@PathVariable Long id) {
        VoucherEntity voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not exist with id :" + id));
        return ResponseEntity.ok(voucher);
    }

    @PutMapping("/vouchers/{id}")
    public ResponseEntity<VoucherEntity> updateVoucher(@PathVariable Long id, @RequestBody VoucherEntity voucherDetails){
        VoucherEntity voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not exist with id :" + id));

        voucher.setVoucherAmount(voucherDetails.getVoucherAmount());
        voucher.setVoucherCode(voucherDetails.getVoucherCode());
        voucher.setVoucherExpiryDate(voucherDetails.getVoucherExpiryDate());

        VoucherEntity updatedVoucher = voucherRepository.save(voucher);
        return ResponseEntity.ok(updatedVoucher);
    }

    // delete employee rest api
    @DeleteMapping("/vouchers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteVoucher(@PathVariable Long id){
        VoucherEntity voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not exist with id :" + id));

        voucherRepository.delete(voucher);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
