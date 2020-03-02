package com.example.Narrow.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Narrow.model.ErrorApi;
import com.example.Narrow.model.Order;

import com.example.Narrow.service.ExcelGenerator;
import com.example.Narrow.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
	@Autowired
	OrderService orderService;
	@Autowired
	ExcelGenerator excelGenerator;

	@GetMapping(value = "/raport")
	public ResponseEntity<?> excelReport(@RequestParam String start, @RequestParam String end,
			@RequestParam(required = false) Long id) throws IOException {
		System.out.print("start: " + start + "end: " + end + "id: " + id);
		ByteArrayInputStream raport = excelGenerator.customersToExcel(start, end, id);
		if (raport != null) {
			String name = "raport_" + start + "-" + end + ".xlsx";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=" + name);
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(raport));
		} else {

			ErrorApi error = new ErrorApi("brak danych w raporcie", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<Order>> getOrders() {
		List<Order> orders = orderService.findAll();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	@PatchMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
		Order order = orderService.findById(id);
		if (order == null) {
			ErrorApi error = new ErrorApi("Brak zamówienia o takim numrze id", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			orderService.delete(order);
			return new ResponseEntity<>(order, HttpStatus.OK);

		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getOrder(@PathVariable Long id) {

		Order order = orderService.findById(id);
		if (order == null) {
			ErrorApi error = new ErrorApi("Brak użytkownika o takim numrze id", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Order> addUOrder(@RequestBody Order order) {

		Order newOrder = orderService.save(order);
		return new ResponseEntity<Order>(newOrder, HttpStatus.OK);

	}

	@PatchMapping
	public ResponseEntity<Order> editOrder(@RequestBody Order order) {

		Order editOrder = orderService.editOrder(order);
		return new ResponseEntity<Order>(editOrder, HttpStatus.OK);
	}
}
